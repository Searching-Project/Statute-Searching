package com.search.statutesearching.repository;

import com.search.statutesearching.entity.Amendment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmendmentRepository extends JpaRepository<Amendment, Long> {
}
