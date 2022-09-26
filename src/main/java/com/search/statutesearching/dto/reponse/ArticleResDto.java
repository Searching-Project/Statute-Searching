package com.search.statutesearching.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
@Getter
@NoArgsConstructor
@AllArgsConstructor
//조문
public class ArticleResDto {
    //조문키
    private Long articleId;

    private String articleKey;
    //조문번호
    private int articleNum;
    // 법령ID
    private String lawSN;
    //조문 제목
    private String title;
    //조문 내용
    private String content;
    //시행일자
    private Date effectiveDate;
    //한시일자
//    @Column(nullable = false)
//    private String field10;
    //조문여부
    private boolean articleYN;
    //조문이동이전
    private String beforeMove;
    //조문이동이후
    private String afterMove;
    //조문변경여부
    private boolean changeYN;
    //조문참고자료
    private String articleReference;
}
