package com.search.statutesearching.dto.reponse;


import com.search.statutesearching.entity.LawMinistry;
import com.search.statutesearching.entity.Ministry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MinistryResDto {
    private Long id;

    private String name;

    private String code;

    private String department;

    private String departmentTel;

    private List<LawMinistry> lawMinistries = new ArrayList<>();

    public MinistryResDto(Ministry ministry){
        this.id = ministry.getId();
        this.name = ministry.getName();
        this.code = ministry.getCode();
        this.department = ministry.getDepartment();
        this.departmentTel = ministry.getDepartmentTel();
        this.lawMinistries = ministry.getLawMinistries();

    }
}
