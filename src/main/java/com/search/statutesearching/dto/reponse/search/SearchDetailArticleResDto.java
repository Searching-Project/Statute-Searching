package com.search.statutesearching.dto.reponse.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

// v2 - 조문 + 항 + 호
@Getter
@Builder
@AllArgsConstructor
public class SearchDetailArticleResDto {
    private Long articleId;
    private String articleTitle;
    private String articleContent;
    private List<SearchDetailParagraphResDto> paragraphs;

    public void addParagraph(SearchDetailParagraphResDto paragraph) {
        this.paragraphs.add(paragraph);
    }
}