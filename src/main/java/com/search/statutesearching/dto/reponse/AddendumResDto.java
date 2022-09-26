package com.search.statutesearching.dto.reponse;


import com.search.statutesearching.entity.Addendum;
import com.search.statutesearching.entity.Law;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
//부칙
public class AddendumResDto {
    private Long id;

    private String publishNumber;

    private String publishDate;

    private String content;

    private Law law;
    public AddendumResDto(Addendum addendum){
        this.id = addendum.getId();
        this.publishNumber = addendum.getPublishNumber();
        this.publishDate = addendum.getPublishDate();
        this.content = addendum.getContent();
    }
}
