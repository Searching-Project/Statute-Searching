package com.search.statutesearching.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Paragraph {

    //항 ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paragraphId;
    //조문ID
    @Column(nullable = false)
    private Long articleId;

    //법령ID
    @Column(nullable = false)
    private String lawSN;
    //항 번호
    @Column
    private String paragraphNum;
    //항 내용
    @Lob
    @Column
    private String paragraphContent;


}
