package com.search.statutesearching.dto.reponse.search;

import com.search.statutesearching.dto.reponse.ArticleResDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

// v1 - 법령 + 조문
@Getter
@Builder
@AllArgsConstructor
public class SearchSimpleResDto {
    private String lawSN;
    private String koreanName;
    private String shorterName;
    private List<SearchArticleResDto> articles;

    public void addArticle(SearchArticleResDto article) {
        this.articles.add(article);
    }
}