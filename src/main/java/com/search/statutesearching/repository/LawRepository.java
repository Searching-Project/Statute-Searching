package com.search.statutesearching.repository;

import com.search.statutesearching.entity.Law;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LawRepository extends JpaRepository<Law,String> {
}
