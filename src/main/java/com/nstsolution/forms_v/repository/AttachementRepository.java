package com.nstsolution.forms_v.repository;

import com.nstsolution.forms_v.model.AttachementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachementRepository extends JpaRepository<AttachementEntity,Long> {
}
