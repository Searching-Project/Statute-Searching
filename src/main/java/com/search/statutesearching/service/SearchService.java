package com.search.statutesearching.service;


import com.search.statutesearching.dto.reponse.*;
import com.search.statutesearching.dto.reponse.search.*;
import com.search.statutesearching.exception.CustomException;
import com.search.statutesearching.repository.LawRepository;
import com.search.statutesearching.repository.PrecedentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final PrecedentRepository precedentRepository;

    @Transactional
    public ResponseDto<?> search(String keyword){
        throw new CustomException(RESULT_NOT_FOUND);
    }

    public List<SearchDetailResDto> convertToDetailDto(List<SearchResDto> searchResDtos) {

        // 최종 법령들을 담을 리스트
        List<SearchDetailResDto> laws = new ArrayList<>();

        SearchDetailResDto prevDetailDto = null;       // 한 법령에 연속된 조문일 경우, 갱신하기 위한 이전 법령
        SearchDetailArticleResDto prevArticleDto = null;       // 한 조문에 연속된 항일 경우, 갱신하기 위한 이전 조문
        SearchDetailParagraphResDto prevParagraphDto = null;       // 한 항에 연속된 호일 경우, 갱신하기 위한 이전 항

        String lastLawSN = null;
        Long lastPId = null;      // 마지막 항 id 저장 - 연속된 호 판별용
        Long lastAId = null;      // 마지막 조문 id 저장 - 연속된 항 판별용

        for (SearchResDto searchResult : searchResDtos) {
            String lawSN = searchResult.getLawsn();
            String koreanName = searchResult.getKorean_name();
            String shorterName = searchResult.getShorter_name();
            Long aArticleId = searchResult.getA_article_id();
            Long pArticleId = searchResult.getP_article_id();
            String articleTitle = searchResult.getTitle();
            String articleContent = searchResult.getContent();
            Long pParagraphId = searchResult.getP_paragrah_id();
            Long hParagraphId = searchResult.getH_paragrah_id();
            String paragraphContent = searchResult.getParagrah_content();
            String hoContent = searchResult.getHo_content();

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
                    .paragraphId(pParagraphId)
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
                    .articleId(aArticleId)
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
    }
}
