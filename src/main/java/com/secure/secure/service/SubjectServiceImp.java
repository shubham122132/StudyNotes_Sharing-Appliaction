package com.secure.secure.service;

import com.secure.secure.model.Subject;
import com.secure.secure.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class SubjectServiceImp implements SubjectService{

    private final SubjectRepository subjectRepository;


    @Override
    public Subject getSubjectById(Long subjectId) {
        return subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found with id: " + subjectId));
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public String createNewSubject(Subject subject) {

        subjectRepository.save(subject);

        return "Subject created successfully";
    }

    @Override
    public String updateSubject(Long subjectId, Subject subject) {
        Subject dbSubject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found with id: " + subjectId));

        dbSubject.setSubjectName(subject.getSubjectName());
        subjectRepository.save(dbSubject);

        return "Subject updated successfully";
    }

    @Override
    public String deleteSubject(Long subjectId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found with id: " + subjectId));

        subjectRepository.delete(subject);

        return "Subject deleted successfully";
    }
}
