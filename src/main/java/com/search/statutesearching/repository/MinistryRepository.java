package com.search.statutesearching.repository;


import com.search.statutesearching.entity.Ministry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MinistryRepository extends JpaRepository<Ministry, Long> {
    Optional<Ministry> findByDepartment(String department);
}
