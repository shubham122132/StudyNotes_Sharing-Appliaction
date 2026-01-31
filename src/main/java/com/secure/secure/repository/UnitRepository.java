package com.secure.secure.repository;

import com.secure.secure.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnitRepository extends JpaRepository<Unit,Long> {
    List<Unit> findBySubSubjectId(Long subSubjectId);
}
