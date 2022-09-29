package com.search.statutesearching.repository;

import com.search.statutesearching.dto.reponse.LawResDto;
import com.search.statutesearching.dto.reponse.SearchResDto;
import com.search.statutesearching.entity.Law;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import java.util.List;

public interface LawRepository extends JpaRepository<Law,String> {

    //페이징 전
    @Query(value = "(" +
            "SELECT t.lawsn, t.korean_name, t.shorter_name, t.content, t.title, t.a_article_id, t.p_article_id, t.paragraph_id AS p_paragraph_id, t.paragraph_content, h.paragraph_id AS h_paragraph_id, h.ho_content FROM (SELECT aa.lawsn, aa.korean_name, aa.shorter_name, aa.content, aa.title, aa.article_id AS a_article_id, p.article_id AS p_article_id, p.paragraph_content, p.paragraph_id  FROM (SELECT l.lawsn, l.korean_name, l.shorter_name , a.content, a.title, a.article_id FROM (SELECT * FROM law AS l2 where MATCH(l2.korean_name, l2.shorter_name) AGAINST(:keyword IN NATURAL LANGUAGE MODE )) AS l         " +
            "JOIN (SELECT a2.lawsn, a2.content, a2.title, a2.article_id FROM article AS a2 where MATCH(a2.title, a2.content) AGAINST(:keyword IN NATURAL LANGUAGE MODE )) AS a using(lawsn)) AS aa " +
            "LEFT JOIN (SELECT p2.article_id, p2.paragraph_content, p2.paragraph_id from paragraph AS p2 WHERE MATCH(p2.paragraph_content) AGAINST(:keyword IN NATURAL LANGUAGE MODE)) AS p ON aa.article_id = p.article_id) AS t " +
            "LEFT JOIN (SELECT * from ho AS h2 WHERE MATCH(h2.ho_content) AGAINST(:keyword IN NATURAL LANGUAGE MODE)) AS h ON t.paragraph_id = h.paragraph_id" +
            ")" +
            "UNION" +
            "(SELECT t.lawsn, t.korean_name, t.shorter_name, t.content, t.title, t.a_article_id, t.p_article_id,t.paragraph_id AS p_paragraph_id, t.paragraph_content, h.paragraph_id AS h_paragraph_id, h.ho_content FROM (SELECT aa.lawsn, aa.korean_name, aa.shorter_name, aa.content, aa.title, aa.article_id AS a_article_id, p.article_id AS p_article_id, p.paragraph_content, p.paragraph_id  FROM (SELECT l.lawsn, l.korean_name, l.shorter_name , a.content, a.title, a.article_id FROM (SELECT * FROM law AS l2 where MATCH(l2.korean_name, l2.shorter_name) AGAINST(:keyword IN NATURAL LANGUAGE MODE )) AS l         " +
            "JOIN (SELECT a2.lawsn, a2.content, a2.title, a2.article_id FROM article AS a2 where MATCH(a2.title, a2.content) AGAINST(:keyword IN NATURAL LANGUAGE MODE )) AS a using(lawsn)) AS aa " +
            "RIGHT JOIN (SELECT p2.article_id, p2.paragraph_content, p2.paragraph_id from paragraph AS p2 WHERE MATCH(p2.paragraph_content) AGAINST(:keyword IN NATURAL LANGUAGE MODE)) AS p ON aa.article_id = p.article_id) AS t " +
            "RIGHT JOIN (SELECT * from ho AS h2 WHERE MATCH(h2.ho_content) AGAINST(:keyword IN NATURAL LANGUAGE MODE)) AS h ON t.paragraph_id = h.paragraph_id" +
            ")" +
            "LIMIT 20 OFFSET 1",nativeQuery = true )
    List<SearchResDto> search0(@Param("keyword")String keyword);
    //페이징 처리
    @Query(value = "(" +
            "SELECT t.lawsn, t.korean_name, t.shorter_name, t.content, t.title, t.a_article_id, t.p_article_id, t.paragraph_id AS p_paragraph_id, t.paragraph_content, h.paragraph_id AS h_paragraph_id, h.ho_content FROM (SELECT aa.lawsn, aa.korean_name, aa.shorter_name, aa.content, aa.title, aa.article_id AS a_article_id, p.article_id AS p_article_id, p.paragraph_content, p.paragraph_id  FROM (SELECT l.lawsn, l.korean_name, l.shorter_name , a.content, a.title, a.article_id FROM (SELECT * FROM law AS l2 where MATCH(l2.korean_name, l2.shorter_name) AGAINST(:keyword IN NATURAL LANGUAGE MODE )) AS l         " +
            "JOIN (SELECT a2.lawsn, a2.content, a2.title, a2.article_id FROM article AS a2 where MATCH(a2.title, a2.content) AGAINST(:keyword IN NATURAL LANGUAGE MODE )) AS a using(lawsn)) AS aa " +
            "LEFT JOIN (SELECT p2.article_id, p2.paragraph_content, p2.paragraph_id from paragraph AS p2 WHERE MATCH(p2.paragraph_content) AGAINST(:keyword IN NATURAL LANGUAGE MODE)) AS p ON aa.article_id = p.article_id) AS t " +
            "LEFT JOIN (SELECT * from ho AS h2 WHERE MATCH(h2.ho_content) AGAINST(:keyword IN NATURAL LANGUAGE MODE)) AS h ON t.paragraph_id = h.paragraph_id" +
            ")" +
            "UNION" +
            "(SELECT t.lawsn, t.korean_name, t.shorter_name, t.content, t.title, t.a_article_id, t.p_article_id,t.paragraph_id AS p_paragraph_id, t.paragraph_content, h.paragraph_id AS h_paragraph_id, h.ho_content FROM (SELECT aa.lawsn, aa.korean_name, aa.shorter_name, aa.content, aa.title, aa.article_id AS a_article_id, p.article_id AS p_article_id, p.paragraph_content, p.paragraph_id  FROM (SELECT l.lawsn, l.korean_name, l.shorter_name , a.content, a.title, a.article_id FROM (SELECT * FROM law AS l2 where MATCH(l2.korean_name, l2.shorter_name) AGAINST(:keyword IN NATURAL LANGUAGE MODE )) AS l         " +
            "JOIN (SELECT a2.lawsn, a2.content, a2.title, a2.article_id FROM article AS a2 where MATCH(a2.title, a2.content) AGAINST(:keyword IN NATURAL LANGUAGE MODE )) AS a using(lawsn)) AS aa " +
            "RIGHT JOIN (SELECT p2.article_id, p2.paragraph_content, p2.paragraph_id from paragraph AS p2 WHERE MATCH(p2.paragraph_content) AGAINST(:keyword IN NATURAL LANGUAGE MODE)) AS p ON aa.article_id = p.article_id) AS t " +
            "RIGHT JOIN (SELECT * from ho AS h2 WHERE MATCH(h2.ho_content) AGAINST(:keyword IN NATURAL LANGUAGE MODE)) AS h ON t.paragraph_id = h.paragraph_id" +
            ")"
            ,countQuery = "select count(total) from ((" +
            "SELECT t.lawsn, t.korean_name, t.shorter_name, t.content, t.title, t.a_article_id, t.p_article_id, t.paragraph_id AS p_paragraph_id, t.paragraph_content, h.paragraph_id AS h_paragraph_id, h.ho_content FROM (SELECT aa.lawsn, aa.korean_name, aa.shorter_name, aa.content, aa.title, aa.article_id AS a_article_id, p.article_id AS p_article_id, p.paragraph_content, p.paragraph_id  FROM (SELECT l.lawsn, l.korean_name, l.shorter_name , a.content, a.title, a.article_id FROM (SELECT * FROM law AS l2 where MATCH(l2.korean_name, l2.shorter_name) AGAINST(:keyword IN NATURAL LANGUAGE MODE )) AS l         " +
            "JOIN (SELECT a2.lawsn, a2.content, a2.title, a2.article_id FROM article AS a2 where MATCH(a2.title, a2.content) AGAINST(:keyword IN NATURAL LANGUAGE MODE )) AS a using(lawsn)) AS aa " +
            "LEFT JOIN (SELECT p2.article_id, p2.paragraph_content, p2.paragraph_id from paragraph AS p2 WHERE MATCH(p2.paragraph_content) AGAINST(:keyword IN NATURAL LANGUAGE MODE)) AS p ON aa.article_id = p.article_id) AS t " +
            "LEFT JOIN (SELECT * from ho AS h2 WHERE MATCH(h2.ho_content) AGAINST(:keyword IN NATURAL LANGUAGE MODE)) AS h ON t.paragraph_id = h.paragraph_id" +
            ")" +
            "UNION" +
            "(SELECT t.lawsn, t.korean_name, t.shorter_name, t.content, t.title, t.a_article_id, t.p_article_id,t.paragraph_id AS p_paragraph_id, t.paragraph_content, h.paragraph_id AS h_paragraph_id, h.ho_content FROM (SELECT aa.lawsn, aa.korean_name, aa.shorter_name, aa.content, aa.title, aa.article_id AS a_article_id, p.article_id AS p_article_id, p.paragraph_content, p.paragraph_id  FROM (SELECT l.lawsn, l.korean_name, l.shorter_name , a.content, a.title, a.article_id FROM (SELECT * FROM law AS l2 where MATCH(l2.korean_name, l2.shorter_name) AGAINST(:keyword IN NATURAL LANGUAGE MODE )) AS l         " +
            "JOIN (SELECT a2.lawsn, a2.content, a2.title, a2.article_id FROM article AS a2 where MATCH(a2.title, a2.content) AGAINST(:keyword IN NATURAL LANGUAGE MODE )) AS a using(lawsn)) AS aa " +
            "RIGHT JOIN (SELECT p2.article_id, p2.paragraph_content, p2.paragraph_id from paragraph AS p2 WHERE MATCH(p2.paragraph_content) AGAINST(:keyword IN NATURAL LANGUAGE MODE)) AS p ON aa.article_id = p.article_id) AS t " +
            "RIGHT JOIN (SELECT * from ho AS h2 WHERE MATCH(h2.ho_content) AGAINST(:keyword IN NATURAL LANGUAGE MODE)) AS h ON t.paragraph_id = h.paragraph_id" +
            ")) as total ",nativeQuery = true )
    List<SearchResDto> search00(@Param("keyword")String keyword,@PageableDefault Pageable pageable);


    //like or 사용
    @Query(value = "select * FROM LAW as l WHERE l.koran_name like %:keyword% " +
            "OR l.shorter_name like %:keyword%",nativeQuery = true )
    List<Law> search(@Param("keyword")String keyword);

    //like union
    @Query(value = "select * FROM LAW as l WHERE l.koran_name like %:keyword% " +
            "union select * FROM LAW as l WHERE l.shorter_name like %:keyword%",nativeQuery = true )
    List<Law> search2(@Param("keyword")String keyword);

    //full text index parser ngram
    @Query(value = "select *(select * FROM LAW as l WHERE MATCH(l.koran_name) AGAINST(:keyword IN BOOLEAN MODE)" +
            "union select * FROM LAW as l WHERE MATCH(l.shorter_name) AGAINST(:keyword IN BOOLEAN MODE))as a2 order by case when a2.koran_name = :original then 1 when a2.shorter_name = :original then 2 else 3 end"
            ,nativeQuery = true )
    List<Law> search3(@Param("keyword")String keyword,@Param("original")String original);

}


