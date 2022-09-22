package com.nstsolution.forms_v.repository;

import com.nstsolution.forms_v.model.SectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends JpaRepository<SectionEntity,Long> {
}
