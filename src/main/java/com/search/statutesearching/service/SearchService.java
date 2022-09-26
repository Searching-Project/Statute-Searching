package com.search.statutesearching.service;


import com.search.statutesearching.dto.reponse.ResponseDto;
import com.search.statutesearching.exception.CustomException;
import com.search.statutesearching.repository.LawRepository;
import com.search.statutesearching.repository.PrecedentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static com.search.statutesearching.exception.ErrorCode.RESULT_NOT_FOUND;

@RequiredArgsConstructor
@Service
@Slf4j
public class SearchService {

    private final LawRepository lawRepository;
    private final PrecedentRepository precedentRepository;

    @Transactional
    public ResponseDto<?> search(String keyword){

        throw new CustomException(RESULT_NOT_FOUND);
    }
}
