package com.search.statutesearching.dto.reponse.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

// v2 - 법령 + 조문 + 항 + 호
@Getter
@Builder
@AllArgsConstructor
public class SearchDetailResDto {
    private String lawSN;
    private String koreanName;
    private String shorterName;
    private List<SearchDetailArticleResDto> searchDetails;

    public void addArticle(SearchDetailArticleResDto article) {
        this.searchDetails.add(article);
    }
}
