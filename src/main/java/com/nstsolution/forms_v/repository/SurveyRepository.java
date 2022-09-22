package com.nstsolution.forms_v.repository;

import com.nstsolution.forms_v.model.SurveyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SurveyRepository extends JpaRepository<SurveyEntity,Long> {

        Optional<SurveyEntity> findById(Long id);

        @Query(" from  SectionEntity survey where survey.id =:id")
        SurveyEntity findSurveyEntitiesById(@Param("id") Long id);

        SurveyEntity findBySurveyTitle(String title);
}
