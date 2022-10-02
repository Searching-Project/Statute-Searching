package com.search.statutesearching.repository;


import com.search.statutesearching.entity.Precedent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PrecedentRepository extends JpaRepository<Precedent, Long> {
    Page<Precedent> findAllByCaseNameContaining(String keyword, Pageable pageable);
//    List<Precedent> findAllByCaseNameContaining(String keyword);

    @Query("SELECT p FROM Precedent p WHERE p.caseName LIKE %:keyword%")
    Page<Precedent> searchByCaseNameLike(@Param("keyword")String keyword, Pageable pageable);
//    List<Precedent> searchByCaseNameLike(@Param("keyword")String keyword);

    @Query(value = "SELECT * FROM precedent WHERE MATCH(case_name, content, judge_holding, judge_reasoning)"
            + "AGAINST ('keyword' IN NATURAL LANGUAGE MODE)", nativeQuery = true)
    Page<Precedent> fullTextSearch(String keyword, Pageable pageable);


    @Query(value = "SELECT * FROM precedent WHERE MATCH(case_name, content, judge_holding, judge_reasoning)"
            + "AGAINST (':keyword' IN NATURAL LANGUAGE MODE)", nativeQuery = true)
    Page<Precedent> fullTextSearchNgram(@Param("keyword")String keyword, Pageable pageable);
}