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
public class Ho {
    //호 ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long HoId;
    //항ID
    @Column(nullable = false)
    private Long paragraphId;

    //법령ID
    @Column(nullable = false)
    private String lawSN;

    //호 번호
    @Column
    private String hoNum;

    //호 내용
    @Lob
    @Column
    private String hoContent;

}
