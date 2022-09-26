package com.search.statutesearching.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PrecedentResDto {
    private Long id;

    private String precSN;

    private String caseName;

    private String caseNumber;

    private String caseTypeName;

    private String caseTypeCode;

    private String courtName;

    private String courtTypeCode;

    private String judgeState;

    private String judgeDate;

    private String judgeType;

    private String judgeHolding;

    private String judgeReasoning;

    private String content;

    private String refArticle;

    private String refPrecedent;

}
