package com.search.statutesearching.repository;

import com.search.statutesearching.dto.reponse.LawResDto;
import com.search.statutesearching.entity.Law;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LawRepository extends JpaRepository<Law,String> {
    //like or 사용
    @Query(value = "select * FROM LAW as l WHERE l.koran_name like %:keyword% " +
            "OR l.shorter_name like %:keyword%",nativeQuery = true )
    List<Law> search(@Param("keyword")String keyword);

    //like union
    @Query(value = "select * FROM LAW as l WHERE l.koran_name like %:keyword% " +
            "union select * FROM LAW as l WHERE l.shorter_name like %:keyword%",nativeQuery = true )
    List<Law> search2(@Param("keyword")String keyword);

    //full text index parser ngram
    @Query(value = "select * FROM LAW as l WHERE MATCH(l.koran_name) AGAINST(:keyword IN BOOLEAN MODE)" +
            "union select * FROM LAW as l WHERE MATCH(l.shorter_name) AGAINST(:keyword IN BOOLEAN MODE)"
            ,nativeQuery = true )
    List<Law> search3(@Param("keyword")String keyword);
}


