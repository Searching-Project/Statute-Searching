package com.search.statutesearching.repository;

import com.search.statutesearching.entity.LawMinistry;
import com.search.statutesearching.entity.LawMinistryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LawMinistryRepository extends JpaRepository<LawMinistry, LawMinistryId> {
}
