package com.search.statutesearching.dto.reponse;

public interface SearchResDto {

    String getLawsn();
    String getKorean_name();
    String getShorter_name();
    String getContent();
    String getTitle();
    Long getA_article_id();
    Long getP_article_id();
    Long getP_paragraph_id();
    String getParagraph_content();
    Long getH_paragraph_id();
    String getHo_content();

}
