package com.search.statutesearching.service;

import com.search.statutesearching.dto.reponse.SearchResDto;
import com.search.statutesearching.dto.reponse.SearchResDto1;
import com.search.statutesearching.dto.reponse.search.*;
import com.search.statutesearching.exception.CustomException;
import com.search.statutesearching.repository.LawRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import static com.search.statutesearching.exception.ErrorCode.RESULT_NOT_FOUND;

@RequiredArgsConstructor
@Service
@Slf4j
public class SearchService {

    private final LawRepository lawRepository;

    @Transactional
    public List<SearchDetailResDto> search(String keyword, Pageable pageable){
        List<SearchResDto> searchResDto =lawRepository.search00(keyword,pageable);
        if(searchResDto==null || searchResDto.size()==0) throw new CustomException(RESULT_NOT_FOUND);
        return convertToDetailDto(searchResDto);
    }

    /* 심플 검색 - 구현 예정
    @Transactional
    public List<SearchDetailResDto> searchSimple(String keyword, Pageable pageable){
        List<SearchResDto> searchResDto =lawRepository.search00(keyword,pageable);
        if(searchResDto==null || searchResDto.size()==0) throw new CustomException(RESULT_NOT_FOUND);
        return convertToSimpleDto(searchResDto);
    }*/

    public List<SearchDetailResDto> convertToDetailDto(List<SearchResDto> searchResDtos) {

        // 최종 법령들을 담을 리스트
        List<SearchDetailResDto> laws = new ArrayList<>();

        SearchDetailResDto prevDetailDto = null;       // 한 법령에 연속된 조문일 경우, 갱신하기 위한 이전 법령
        SearchDetailArticleResDto prevArticleDto = null;       // 한 조문에 연속된 항일 경우, 갱신하기 위한 이전 조문
        SearchDetailParagraphResDto prevParagraphDto = null;       // 한 항에 연속된 호일 경우, 갱신하기 위한 이전 항

        String lastLawSN = "000000";
        Long lastPId = 0L;      // 마지막 항 id 저장 - 연속된 호 판별용
        Long lastAId = 0L;      // 마지막 조문 id 저장 - 연속된 항 판별용

        for (SearchResDto searchResult : searchResDtos) {
            String lawSN = searchResult.getLawsn();
            String koreanName = searchResult.getKorean_name();
            String shorterName = searchResult.getShorter_name();
            Long aArticleId = searchResult.getA_article_id();
            Long pArticleId = searchResult.getP_article_id();
            String articleTitle = searchResult.getTitle();
            String articleContent = searchResult.getContent();
            Long pParagraphId = searchResult.getP_paragraph_id();
            Long hParagraphId = searchResult.getH_paragraph_id();
            String paragraphContent = searchResult.getParagraph_content();
            String hoContent = searchResult.getHo_content();

            boolean continuousHo = Objects.equals(lastPId, hParagraphId);
            lastPId = hParagraphId;

            // 1-1 : 연속된 호라면, 호만 추가하고 반복문 넘기기
            if (continuousHo && hoContent != null) {
                prevParagraphDto.addHo(SearchDetailHoResDto.builder().hoContent(hoContent).build());
                continue;
            }

            SearchDetailParagraphResDto paragraph = null;
            List<SearchDetailHoResDto> hos = new ArrayList<>();

            if (hoContent != null) {

                // 1-2 : 연속된 호가 아니라면, 새로 호, 항을 만들기
                hos.add(SearchDetailHoResDto.builder().hoContent(hoContent).build());

                paragraph = SearchDetailParagraphResDto.builder()
                        .paragraphId((pParagraphId != null) ? pParagraphId : hParagraphId )
                        .paragraphContent(paragraphContent)
                        .hos(hos)
                        .build();

                prevParagraphDto = paragraph;
            }

            // 2단계 : 같은 조문의 연속된 항인지 판별하기
            boolean continuousParagraph = Objects.equals(lastAId, pArticleId);

            // 2-1. 연속된 항이라면, 항만 추가하고 반복문 넘기기
            if (continuousParagraph && paragraph != null) {
                prevArticleDto.addParagraph(paragraph);
                continue;
            }

            SearchDetailArticleResDto article = null;
            List<SearchDetailParagraphResDto> paragraphs = new ArrayList<>();

            if (paragraph != null) {
                // 2-2. 연속된 항이 아니라면, 새로운 항, 조문을 만들기
                paragraphs.add(paragraph);
            }

            if (articleContent != null) {
                article = SearchDetailArticleResDto.builder()
                        .articleId((aArticleId != null) ? aArticleId : pArticleId)
                        .articleTitle(articleTitle)
                        .articleContent(articleContent)
                        .paragraphs(paragraphs)
                        .build();
                prevArticleDto = article;
            }

            // 3단계 : 같은 법령의 연속된 조문인지 판별하기
            boolean continuousArticle = Objects.equals(lastLawSN, lawSN);

            // 3-1. 연속된 조문이라면, 이전 법령에 조문 추가하고 반복문 넘기기
            if (continuousArticle && articleContent != null) {
                prevDetailDto.addArticle(article);
                continue;
            }

            List<SearchDetailArticleResDto> articles = new ArrayList<>();

            if (article != null) {
                // 3-2. 연속된 조문이 아니라면, 새로운 조문 만들기
                articles.add(article);
            }

            SearchDetailResDto law = SearchDetailResDto.builder()
                    .lawSN(lawSN)
                    .koreanName(koreanName)
                    .shorterName(shorterName)
                    .articles(articles)
                    .build();

            laws.add(law);
        }
        return laws;
    }

    /*public List<SearchSimpleResDto> convertToSimpleDto(List<SearchResDto> searchResDtos) {

        // 최종 법령들을 담을 리스트
        List<SearchSimpleResDto> laws = new ArrayList<>();

        SearchSimpleResDto prevSimpleDto = null;       // 한 법령에 연속된 조문일 경우, 갱신하기 위한 이전 법령
        SearchArticleResDto prevArticleDto = null;       // 한 조문에 연속된 항일 경우, 갱신하기 위한 이전 조문

        String lastLawSN = null;
        Long lastPId = null;      // 마지막 항 id 저장 - 연속된 호 판별용
        Long lastAId = null;      // 마지막 조문 id 저장 - 연속된 항 판별용

        for (SearchResDto searchResult : searchResDtos) {
            String lawSN = searchResult.getLawsn();
            String koreanName = searchResult.getKoreanName();
            String shorterName = searchResult.getShorterName();
            Long aArticleId = searchResult.getAArticleId();
            Long pArticleId = searchResult.getPArticleId();
            String articleTitle = searchResult.getTitle();
            String articleContent = searchResult.getContent();
            Long pParagraphId = searchResult.getPParagraphId();
            Long hParagraphId = searchResult.getHParagraphId();
            String paragraphContent = searchResult.getParagraphContent();
            String hoContent = searchResult.getHoContent();

            // 1단계 : 같은 항의 연속된 호인지 판별하기
            boolean continuousHo = Objects.equals(lastPId, hParagraphId);
            lastPId = hParagraphId;

            // 1-1 : 연속된 호라면, 호만 추가하고 반복문 넘기기
            if (continuousHo) {
                prevParagraphDto.addHo(SearchDetailHoResDto.builder().hoContent(hoContent).build());
                continue;
            }

            // 1-2 : 연속된 호가 아니라면, 새로 호, 항을 만들기
            List<SearchDetailHoResDto> hos = new ArrayList<>();
            hos.add(SearchDetailHoResDto.builder().hoContent(hoContent).build());

            SearchDetailParagraphResDto paragraph = SearchDetailParagraphResDto.builder()
                    .paragraphId((pParagraphId != null) ? pParagraphId : hParagraphId )
                    .paragraphContent(paragraphContent)
                    .hos(hos)
                    .build();

            prevParagraphDto = paragraph;

            // 2단계 : 같은 조문의 연속된 항인지 판별하기
            boolean continuousParagraph = Objects.equals(lastAId, pArticleId);

            // 2-1. 연속된 항이라면, 항만 추가하고 반복문 넘기기
            if (continuousParagraph) {
                prevArticleDto.addParagraph(paragraph);
                continue;
            }

            // 2-2. 연속된 항이 아니라면, 새로운 항, 조문을 만들기
            List<SearchDetailParagraphResDto> paragraphs = new ArrayList<>();
            paragraphs.add(paragraph);

            SearchDetailArticleResDto article = SearchDetailArticleResDto.builder()
                    .articleId((aArticleId != null) ? aArticleId : pArticleId)
                    .articleTitle(articleTitle)
                    .articleContent(articleContent)
                    .paragraphs(paragraphs)
                    .build();

            prevArticleDto = article;

            // 3단계 : 같은 법령의 연속된 조문인지 판별하기
            boolean continuousArticle = Objects.equals(lastLawSN, lawSN);

            // 3-1. 연속된 조문이라면, 이전 법령에 조만 추가하고 반복문 넘기기
            if (continuousArticle) {
                prevDetailDto.addArticle(article);
                continue;
            }

            // 3-2. 연속된 조문이 아니라면, 새로운 조문 만들기
            List<SearchDetailArticleResDto> articles = new ArrayList<>();
            articles.add(article);

            SearchDetailResDto law = SearchDetailResDto.builder()
                    .lawSN(lawSN)
                    .koreanName(koreanName)
                    .shorterName(shorterName)
                    .searchDetails(articles)
                    .build();

            laws.add(law);
        }
        return laws;
    }*/
}
