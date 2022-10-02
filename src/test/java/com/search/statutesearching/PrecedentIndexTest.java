package com.search.statutesearching;

import com.search.statutesearching.repository.PrecedentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StopWatch;

@SpringBootTest
public class PrecedentIndexTest {

    @Autowired
    private PrecedentRepository precedentRepository;

    @DisplayName("JPA - contains")
    @Test
    void jpa_contains_Test() {
        // given
        StopWatch stopWatch = new StopWatch();
        Pageable pageable = PageRequest.of(1, 100);

        // when
        stopWatch.start();
//        precedentRepository.findAllByCaseNameContaining("부당해고");
        precedentRepository.findAllByCaseNameContaining("부당해고", pageable);
        stopWatch.stop();

        // then
        System.out.println(stopWatch.prettyPrint());
    }

    @DisplayName("Native @Query like")
    @Test
    void like_Test() {
        // given
        StopWatch stopWatch = new StopWatch();
        Pageable pageable = PageRequest.of(1, 100);

        // when
        stopWatch.start();
//        precedentRepository.findAllByCaseNameContaining("부당해고");
        precedentRepository.findAllByCaseNameContaining("부당해고", pageable);
        stopWatch.stop();

        // then
        System.out.println(stopWatch.prettyPrint());
    }

    @DisplayName("Full Text Search")
    @Test
    void fullTextSearch_Test() {
        // given
        Pageable pageable = PageRequest.of(1, 100);
        StopWatch stopWatch = new StopWatch();

        // when
        stopWatch.start();
        precedentRepository.fullTextSearch("부당해고", pageable);
        stopWatch.stop();

        // then
        System.out.println(stopWatch.prettyPrint());
    }

    @DisplayName("Full Text Search - nGram")
    @Test
    void fullTextSearch_ngram_Test() {
        // given
        Pageable pageable = PageRequest.of(1, 100);
        StopWatch stopWatch = new StopWatch();

        // when
        stopWatch.start();
        precedentRepository.fullTextSearchNgram("부당해고", pageable);
        stopWatch.stop();

        // then
        System.out.println(stopWatch.prettyPrint());
    }
}