package com.search.statutesearching;

import com.search.statutesearching.repository.LawRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class LawTest {

    @Autowired
    private LawRepository lawRepository;

    private String keyword;

    @BeforeEach
    void beforeAll(){
       keyword = "법령";
    }


    @Test
    void index_test() {
        // given
        StopWatch stopWatch = new StopWatch();

        // when
        stopWatch.start("like or 사용한 쿼리");
        lawRepository.search(keyword);
        stopWatch.stop();

        stopWatch.start("like union 사용한 쿼리");
        lawRepository.search2(keyword);
        stopWatch.stop();

        stopWatch.start("full text index 사용한 쿼리");
        lawRepository.search3(keyword);
        stopWatch.stop();

        // then
        System.out.println(stopWatch.prettyPrint());
    }


}