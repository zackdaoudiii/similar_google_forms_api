package com.nstsolution.forms_v.repository;

import com.nstsolution.forms_v.model.AnswerUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerUserRepository extends JpaRepository<AnswerUserEntity,Long> {
}
