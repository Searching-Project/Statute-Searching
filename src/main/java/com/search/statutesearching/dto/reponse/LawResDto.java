package com.search.statutesearching.dto.reponse;


import com.search.statutesearching.entity.Law;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LawResDto {
    private String lawSN;
    //법령키
    private String lawKey;
    //법령명
    private String koranName;
    private String chineseName;
    //법령명약칭
    private String shorterName;
    //언어
    private String language;
    //한글법령여부
    private boolean koreanYN;
    //법종구분
    private String type;
    //법종구분코드
    private String typeCode;
    //공보법령여부
    private boolean effectiveYN;
    //공포번호
    private String publishNumber;
    //공포일자
    private Date publishDate;
    //시행일자
    private Date effectiveDate;
    //제명 변경 여부
    private boolean changeYN;


    public LawResDto(Law law){
        this.lawSN = law.getLawSN();
        this.lawKey = law.getLawKey();
        this.koranName = law.getKoranName();
        this.chineseName = law.getChineseName();
        this.shorterName = law.getShorterName();
        this.language = law.getLanguage();
        this.koreanYN = law.isKoreanYN();
        this.type = law.getType();
        this.typeCode = law.getTypeCode();
        this.effectiveYN = law.isEffectiveYN();
        this.publishNumber = law.getPublishNumber();
        this.publishDate = law.getPublishDate();
        this.effectiveDate= law.getEffectiveDate();
        this.changeYN = law.isChangeYN();

    }
}
