package com.search.statutesearching.repository;


import com.search.statutesearching.entity.Precedent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrecedentRepository extends JpaRepository<Precedent, Long> {
}