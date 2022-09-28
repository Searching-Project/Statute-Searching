package com.search.statutesearching.repository;


import com.search.statutesearching.entity.Ministry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MinistryRepository extends JpaRepository<Ministry, Long> {
    Optional<Ministry> findByDepartment(String department);

    List<Ministry> findAllByNameContainingOrDepartmentContaining(String keyword);
    List<Ministry> findAllByNameLikeOrDepartmentLike(String keyword);

//    @Query(value = "SELECT * FROM ministry WHERE MATCH(name, department)"
//            + "AGAINST ('keyword' IN NATURAL LANGUAGE MODE)", nativeQuery = true)
//    Page<Ministry> fullTextSearch(String keyword, Pageable pageable);

//    @Query(value = "SELECT * FROM ministry WHERE MATCH(ministry_name, department_name)"
//            + "AGAINST ('keyword' IN NATURAL LANGUAGE MODE)", nativeQuery = true)
//    List<Ministry> fullTextSearch(String keyword);


//    @Query(value = "SELECT * FROM ministry WHERE MATCH(name, department)"
//            + "AGAINST (':keyword' IN NATURAL LANGUAGE MODE)", nativeQuery = true)
//    Page<Ministry> fullTextSearchNgram(@Param("keyword")String keyword, Pageable pageable);

//    @Query(value = "SELECT * FROM ministry WHERE MATCH(name, department)"
//            + "AGAINST (':keyword' IN NATURAL LANGUAGE MODE)", nativeQuery = true)
//    List<Ministry> fullTextSearchNgram(@Param("keyword")String keyword);

}
