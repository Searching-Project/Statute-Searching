package com.search.statutesearching.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Article {

    //조문키
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;

    @Column(nullable = false)
    private String articleKey;
    //조문번호
    @Column(nullable = false)
    private int articleNum;
    // 법령ID
    @Column(nullable = false)
    private String lawSN;
    //조문 제목
    @Column
    private String title;
    //조문 내용
    @Lob
    @Column(nullable = false)
    private String content;
    //시행일자
    @Column
    private Date effectiveDate;
    //한시일자
//    @Column(nullable = false)
//    private String field10;
    //조문여부
    @Column
    private boolean articleYN;
    //조문이동이전
    @Column
    private String beforeMove;
    //조문이동이후
    @Column
    private String afterMove;
    //조문변경여부
    @Column
    private boolean changeYN;
    //조문참고자료
    @Column
    private String articleReference;

}
