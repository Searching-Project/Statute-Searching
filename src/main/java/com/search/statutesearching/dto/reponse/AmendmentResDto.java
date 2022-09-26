package com.search.statutesearching.dto.reponse;

import com.search.statutesearching.entity.Amendment;
import com.search.statutesearching.entity.Law;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
//제개정문
public class AmendmentResDto {
    private Long id;

    private String type;

    private String content;

    private String reasonContent;

    private Law law;

    public AmendmentResDto(Amendment amendment){
        this.id = amendment.getId();
        this.type = amendment.getType();
        this.content =amendment.getContent();
        this.reasonContent = amendment.getReasonContent();
        this.law = amendment.getLaw();
    }
}
