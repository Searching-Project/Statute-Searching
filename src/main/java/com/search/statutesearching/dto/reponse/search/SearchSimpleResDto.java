package com.search.statutesearching.dto.reponse.search;

import com.search.statutesearching.dto.reponse.ArticleResDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

// v1 - 법령 + 조문
@Getter
@AllArgsConstructor
public class SearchSimpleResDto {
    private String lawSN;
    private String koreanName;
    private String shorterName;
    private List<ArticleResDto> articles;
}