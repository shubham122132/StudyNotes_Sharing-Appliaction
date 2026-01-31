package com.secure.secure.controller;

import com.secure.secure.model.SubSubject;
import com.secure.secure.service.SubSubjectService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SubSubjectController {

    private final SubSubjectService subSubjectService;

    // CREATE
    @PostMapping("/admin/sub-subjects")
    public SubSubject create(
            @Valid @RequestBody SubSubject subSubject,
            @RequestParam Long subjectId) {

        return subSubjectService.createSubSubject(subSubject, subjectId);
    }

    // READ ALL
    @GetMapping("/public/sub-subjects")
    public List<SubSubject> getAll() {
        return subSubjectService.getAllSubSubjects();
    }

    // READ BY SUBJECT
    @GetMapping("public/sub-subjects/{subjectId}")
    public List<SubSubject> getBySubject(@PathVariable Long subjectId) {
        return subSubjectService.getSubSubjectsBySubject(subjectId);
    }

    // UPDATE
    @PutMapping("/admin/sub-subjects/{id}")
    public SubSubject update(
            @PathVariable Long id,
            @RequestBody SubSubject subSubject) {

        return subSubjectService.updateSubSubject(id, subSubject);
    }

    // DELETE
    @DeleteMapping("/admin/sub-subjects/{id}")
    public void delete(@PathVariable Long id) {
        subSubjectService.deleteSubSubject(id);
    }
}
