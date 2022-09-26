package com.search.statutesearching.dto.reponse;


import com.search.statutesearching.entity.Paragraph;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ParagraphResDto {

    private Long paragraphId;
    //조문ID
    private Long articleId;

    //법령ID
    private String lawSN;
    //항 번호
    private String paragraphNum;
    //항 내용
    private String paragraphContent;

    public ParagraphResDto(Paragraph paragraph){
        this.lawSN = paragraph.getLawSN();
        this.articleId = paragraph.getArticleId();
        this.paragraphNum = paragraph.getParagraphNum();
        this.paragraphContent = paragraph.getParagraphContent();
    }
}
