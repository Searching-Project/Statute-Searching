package com.search.statutesearching.service;

import com.search.statutesearching.dto.reponse.SearchResDto;
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

    // 단순 검색 - 법령, 조까지
    @Transactional
    public List<SearchSimpleResDto> simpleSearch(String keyword, Pageable pageable){
        List<SearchResDto> searchResDto =lawRepository.search00(keyword,pageable);
        if(searchResDto==null || searchResDto.size()==0) throw new CustomException(RESULT_NOT_FOUND);
        return convertToSimpleDto(searchResDto);
    }

    // 상세 검색 - 법령, 조, 항, 호까지
    @Transactional
    public List<SearchDetailResDto> search(String keyword, Pageable pageable) {
        List<SearchResDto> searchResDto = lawRepository.search00(keyword, pageable);
        if (searchResDto == null || searchResDto.size() == 0) throw new CustomException(RESULT_NOT_FOUND);
        return convertToDetailDto(searchResDto);
    }

    public List<SearchSimpleResDto> convertToSimpleDto(List<SearchResDto> searchResDtos) {

        List<SearchSimpleResDto> laws = new ArrayList<>();        // 최종 법령들을 담을 리스트
        SearchSimpleResDto prevSimpleDto = null;            // 한 법령에 연속된 조문일 경우, 갱신하기 위한 이전 법령
        String lastLawSN = null;            // 마지막 조문 id 저장 - 연속된 항 판별용

        for (SearchResDto searchResult : searchResDtos) {
            String lawSN = searchResult.getLawsn();
            String koreanName = searchResult.getKorean_name();
            String shorterName = searchResult.getShorter_name();
            Long aArticleId = searchResult.getA_article_id();
            String articleTitle = searchResult.getTitle();
            String articleContent = searchResult.getContent();

            SearchArticleResDto article = null;

            // 1단계 : 조문 만들기
            if (aArticleId != null) {
                article = SearchArticleResDto.builder()
                        .articleId(aArticleId)
                        .articleTitle(articleTitle)
                        .articleContent(articleContent)
                        .build();
            }

            // 2단계 : 법령의 연속된 조문인지 판별하기
            boolean continuousArticle = Objects.equals(lastLawSN, lawSN);
            lastLawSN = lawSN;

            // 2-1. 연속된 조문이라면, 이전 법령에 조만 추가하고 반복문 넘기기
            if (continuousArticle && article != null) {
                prevSimpleDto.addArticle(article);
                continue;
            }

            // 2-2. 연속된 조문이 아니라면, 새로운 조문 리스트 생성하기
            List<SearchArticleResDto> articles = new ArrayList<>();
            articles.add(article);

            // 3단계 : 법령 추가하기
            SearchSimpleResDto law = SearchSimpleResDto.builder()
                    .lawSN(lawSN)
                    .koreanName(koreanName)
                    .shorterName(shorterName)
                    .articles(articles)
                    .build();
            prevSimpleDto = law;

            laws.add(law);
        }
        return laws;
    }

    public List<SearchDetailResDto> convertToDetailDto(List<SearchResDto> searchResDtos) {

        // 최종 법령들을 담을 리스트
        List<SearchDetailResDto> laws = new ArrayList<>();

        SearchDetailResDto prevDetailDto = null;       // 한 법령에 연속된 조문일 경우, 갱신하기 위한 이전 법령
        SearchDetailArticleResDto prevArticleDto = null;       // 한 조문에 연속된 항일 경우, 갱신하기 위한 이전 조문
        SearchDetailParagraphResDto prevParagraphDto = null;       // 한 항에 연속된 호일 경우, 갱신하기 위한 이전 항

        Long lastPId = 0L;      // 마지막 항 id 저장 - 연속된 호 판별용
        Long lastAId = 0L;      // 마지막 조문 id 저장 - 연속된 항 판별용
        String lastLawSN = "000000";

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
                        .paragraphId((pParagraphId != null) ? pParagraphId : hParagraphId)
                        .paragraphContent(paragraphContent)
                        .hos(hos)
                        .build();
                prevParagraphDto = paragraph;
            }

            // 2단계 : 같은 조문의 연속된 항인지 판별하기
            boolean continuousParagraph = Objects.equals(lastAId, pArticleId);
            lastAId = aArticleId;

            // 2-1. 연속된 항이라면, 항만 추가하고 반복문 넘기기
            if (continuousParagraph && paragraph != null) {
                prevArticleDto.addParagraph(paragraph);
                continue;
            }

            List<SearchDetailParagraphResDto> paragraphs = new ArrayList<>();

            // 2-2. 연속된 항이 아니라면, 새로운 항, 조문을 만들기
            if (paragraph != null) {
                paragraphs.add(paragraph);
            }

            // 3단계 : 같은 법령의 연속된 조문인지 판별하기
            boolean continuousArticle = Objects.equals(lastLawSN, lawSN);
            lastLawSN = lawSN;

            SearchDetailArticleResDto article = null;

            if (aArticleId != null) {
                article = SearchDetailArticleResDto.builder()
                        .articleId((aArticleId != null) ? aArticleId : pArticleId)
                        .articleTitle(articleTitle)
                        .articleContent(articleContent)
                        .paragraphs(paragraphs)
                        .build();
                prevArticleDto = article;
            }

            // 3-1. 연속된 조문이라면, 이전 법령에 조문 추가하고 반복문 넘기기
            if (continuousArticle && article != null) {
                prevDetailDto.addArticle(article);
                continue;
            }

            List<SearchDetailArticleResDto> articles = new ArrayList<>();

            // 3-2. 연속된 조문이 아니라면, 새로운 조문 만들기
            articles.add(article);

            // 4. 법령 만들기
            SearchDetailResDto law = SearchDetailResDto.builder()
                    .lawSN(lawSN)
                    .koreanName(koreanName)
                    .shorterName(shorterName)
                    .articles(articles)
                    .build();
            prevDetailDto = law;

            laws.add(law);
        }
        return laws;
    }

}
