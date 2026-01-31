package com.secure.secure.service;

import com.secure.secure.model.SubSubject;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface SubSubjectService {
    SubSubject createSubSubject(SubSubject subSubject,Long subjectId);

    List<SubSubject> getAllSubSubjects();

    List<SubSubject> getSubSubjectsBySubject(Long subjectId);

    SubSubject updateSubSubject(Long subSubjectId, SubSubject subSubject);

    void deleteSubSubject(Long subSubjectId);
}
