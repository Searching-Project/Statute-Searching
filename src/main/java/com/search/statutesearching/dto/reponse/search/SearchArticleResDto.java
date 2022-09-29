package com.search.statutesearching.dto.reponse.search;

import lombok.AllArgsConstructor;
import lombok.Getter;

// v1 - 조문
@Getter
@AllArgsConstructor
public class SearchArticleResDto {
    private Long articleId;
    private String articleTitle;
    private String articleContent;
}