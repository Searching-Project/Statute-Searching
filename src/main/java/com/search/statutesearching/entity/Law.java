package com.search.statutesearching.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Law {
    //법령ID
    @Id
    private String lawSN;
    //법령키
    @Column()
    private String lawKey;
    //법령명
    @Column(nullable = false)
    private String koranName;
    @Column
    private String chineseName;
    //법령명약칭
    @Column
    private String shorterName;
    //언어
    @Column(nullable = false)
    private String language;
    //한글법령여부
    @Column
    private boolean koreanYN;
    //법종구분
    @Column(nullable = false)
    private String type;
    //법종구분코드
    @Column(nullable = false)
    private String typeCode;
    //공보법령여부
    @Column
    private boolean effectiveYN;
    //공포번호
    @Column(nullable = false)
    private String publishNumber;
    //공포일자
    @Column(nullable = false)
    private Date publishDate;
    //시행일자
    @Column
    private Date effectiveDate;
    //제명 변경 여부
    @Column
    private boolean changeYN;


}
