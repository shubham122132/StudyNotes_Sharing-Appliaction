package com.secure.secure.service;

import com.secure.secure.model.Subject;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface SubjectService {
    String createNewSubject(Subject subject);

    String updateSubject(Long subjectId, Subject subject);

    Subject getSubjectById(Long subjectId);

    List<Subject> getAllSubjects();

    String deleteSubject(Long subjectId);
}
