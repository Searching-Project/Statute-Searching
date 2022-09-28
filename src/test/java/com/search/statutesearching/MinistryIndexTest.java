package com.search.statutesearching;

import com.search.statutesearching.repository.MinistryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StopWatch;

public class MinistryIndexTest {

    @Autowired
    private MinistryRepository ministryRepository;

    @DisplayName("JPA - containing")
    @Test
    void jpa_contains_Test() {
        // given
        StopWatch stopWatch = new StopWatch();

        // when
        stopWatch.start();
        ministryRepository.findAllByNameContainingOrDepartmentContaining("교육");
        stopWatch.stop();

        // then
        System.out.println(stopWatch.prettyPrint());
    }

    @DisplayName("JPA - like")
    @Test
    void like_Test() {
        // given
        StopWatch stopWatch = new StopWatch();

        // when
        stopWatch.start();
        ministryRepository.findAllByNameLikeOrDepartmentLike("교육");
        stopWatch.stop();

        // then
        System.out.println(stopWatch.prettyPrint());
    }

    @DisplayName("Full Text Search")
    @Test
    void fullTextSearch_Test() {
        // given
        Pageable pageable = PageRequest.of(10, 100);
        StopWatch stopWatch = new StopWatch();

        // when
        stopWatch.start();
//        ministryRepository.fullTextSearch("교육", pageable);
//        ministryRepository.fullTextSearch("교육");
        stopWatch.stop();

        // then
        System.out.println(stopWatch.prettyPrint());
    }

    @DisplayName("Full Text Search - nGram")
    @Test
    void fullTextSearch_ngram_Test() {
        // given
        Pageable pageable = PageRequest.of(10, 100);
        StopWatch stopWatch = new StopWatch();

        // when
        stopWatch.start();
//        ministryRepository.fullTextSearchNgram("교육", pageable);
//        ministryRepository.fullTextSearchNgram("교육");
        stopWatch.stop();

        // then
        System.out.println(stopWatch.prettyPrint());
    }
}
