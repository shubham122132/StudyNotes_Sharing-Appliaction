package com.secure.secure.service;

import com.secure.secure.model.SubSubject;
import com.secure.secure.model.Subject;
import com.secure.secure.repository.SubSubjectRepository;
import com.secure.secure.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubSubjectServiceImp implements SubSubjectService {

    private final SubSubjectRepository subSubjectRepo;
    private final SubjectRepository subjectRepo;

    @Override
    public SubSubject createSubSubject(SubSubject subSubject,Long subjectId) {

        Subject subject = subjectRepo.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found with id: " + subjectId));

        SubSubject sub = new SubSubject();
        sub.setName(subSubject.getName());
        sub.setSubject(subject);

        return subSubjectRepo.save(sub);
    }

    @Override
    public List<SubSubject> getAllSubSubjects() {
        return subSubjectRepo.findAll();
    }

    @Override
    public List<SubSubject> getSubSubjectsBySubject(Long subjectId) {

        // BETTER than filtering in controller
        return subSubjectRepo.findBySubjectId(subjectId);
    }

    @Override
    public SubSubject updateSubSubject(Long subSubjectId, SubSubject subSubject) {

        SubSubject oldSubSubject = subSubjectRepo.findById(subSubjectId)
                .orElseThrow(() -> new RuntimeException("SubSubject not found with id: " + subSubjectId));

        oldSubSubject.setName(subSubject.getName());
        return subSubjectRepo.save(oldSubSubject);
    }

    @Override
    public void deleteSubSubject(Long subSubjectId) {

        if (!subSubjectRepo.existsById(subSubjectId)) {
            throw new RuntimeException("SubSubject not found with id: " + subSubjectId);
        }

        subSubjectRepo.deleteById(subSubjectId);
    }
}

