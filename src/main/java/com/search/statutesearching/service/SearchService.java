package com.search.statutesearching.service;


import com.search.statutesearching.dto.reponse.SearchResDto;
import com.search.statutesearching.exception.CustomException;
import com.search.statutesearching.repository.LawRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import static com.search.statutesearching.exception.ErrorCode.RESULT_NOT_FOUND;

@RequiredArgsConstructor
@Service
@Slf4j
public class SearchService {

    private final LawRepository lawRepository;

    @Transactional
    public List<SearchResDto> search(String keyword, Pageable pageable){
        List<SearchResDto> searchResDto =lawRepository.search00(keyword,pageable);
        if(searchResDto==null || searchResDto.size()==0) throw new CustomException(RESULT_NOT_FOUND);

        return searchResDto;
    }
}
