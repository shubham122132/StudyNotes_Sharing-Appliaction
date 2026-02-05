package com.secure.secure.controller;

import com.secure.secure.model.Subject;
import com.secure.secure.service.SubjectService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;


    // USER + ADMIN
    @GetMapping("/public/subjects/{subjectId}")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Subject> getSubjectById(@PathVariable Long subjectId) {
        return ResponseEntity.ok(subjectService.getSubjectById(subjectId));
    }

    // USER + ADMIN
    @GetMapping("/public/subjects")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<Subject>> getAllSubjects() {
        return ResponseEntity.ok(subjectService.getAllSubjects());
    }

    // ADMIN only
    @PostMapping("/admin/subjects")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> addSubject(
            @Valid @RequestBody Subject subject) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(subjectService.createNewSubject(subject));
    }

    // ADMIN only
    @PutMapping("/admin/subjects/{subjectId}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateSubject(
            @PathVariable Long subjectId,
            @RequestBody Subject subject) {

        return ResponseEntity.ok(
                subjectService.updateSubject(subjectId, subjecto)
        );
    }

    // ADMIN only
    @DeleteMapping("/admin/subjects/{subjectId}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteSubject(@PathVariable Long subjectId) {
        return ResponseEntity.ok(
                subjectService.deleteSubject(subjectId)
        );
    }
}



