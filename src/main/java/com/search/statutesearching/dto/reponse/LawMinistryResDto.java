package com.search.statutesearching.dto.reponse;


import com.search.statutesearching.entity.Law;
import com.search.statutesearching.entity.Ministry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LawMinistryResDto {
    private Law law;
    private Ministry ministry;
}
