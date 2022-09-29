package com.search.statutesearching.controller;

import com.search.statutesearching.dto.reponse.ResponseDto;
import com.search.statutesearching.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RequestMapping("/api/search")
@RestController
public class SearchController {
    private final SearchService searchService;

    @GetMapping("/{keyword}")
    public ResponseDto<?> s  earch(@PathVariable String keyword, @PageableDefault(page = 0, size = 20) Pageable pageable){
        System.out.println(keyword);
        return ResponseDto.success(searchService.search(keyword,pageable));
    }
}
