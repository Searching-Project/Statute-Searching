package com.search.statutesearching.dto.reponse.search;

import lombok.AllArgsConstructor;
import lombok.Getter;

// Repository애서 받을 Dto
@Getter
@AllArgsConstructor
public class SearchResDto {
    private String lawsn;
    private String korean_name;
    private String shorter_name;
    private String content;
    private String title;
    private Long a_article_id;
    private Long p_article_id;
    private Long p_paragrah_id;
    private String paragrah_content;
    private Long h_paragrah_id;
    private String ho_content;
}