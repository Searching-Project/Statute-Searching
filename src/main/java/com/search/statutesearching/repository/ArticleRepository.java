package com.search.statutesearching.repository;

import com.search.statutesearching.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article,Long> {
}

