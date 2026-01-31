package com.secure.secure.repository;

import com.secure.secure.model.SubSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubSubjectRepository extends JpaRepository<SubSubject,Long> {
    List<SubSubject> findBySubjectId(Long subjectId);
}
