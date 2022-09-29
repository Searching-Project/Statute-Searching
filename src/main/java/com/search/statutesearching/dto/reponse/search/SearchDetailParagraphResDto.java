package com.search.statutesearching.dto.reponse.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

// v2 - 항 + 호
@Getter
@Builder
@AllArgsConstructor
public class SearchDetailParagraphResDto {
    private Long paragraphId;
    private String paragraphContent;
    private List<SearchDetailHoResDto> hos;

    public void addHo(SearchDetailHoResDto ho) {
        this.hos.add(ho);
    }
}
