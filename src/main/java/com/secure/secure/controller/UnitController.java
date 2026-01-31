package com.secure.secure.controller;

import com.secure.secure.model.Unit;
import com.secure.secure.service.UnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/units")
@RequiredArgsConstructor
public class UnitController {

    private final UnitService unitService;


    // CREATE (UPLOAD PDF)
    @PostMapping("/upload")
    public Unit create(
            @RequestParam String name,
            @RequestParam Long subSubjectId,
            @RequestParam MultipartFile pdf
    ) throws IOException {

        return unitService.createUnit(name, subSubjectId, pdf);
    }

    // READ ALL
    @GetMapping
    public List<Unit> getAll() {
        return unitService.getAllUnits();
    }

    // READ BY SUB-SUBJECT
    @GetMapping("/sub-subject/{id}")
    public List<Unit> getBySubSubject(@PathVariable Long id) {
        return unitService.getUnitsBySubSubject(id);
    }

    // UPDATE (UNIT NAME ONLY)
    @PutMapping("/{id}")
    public Unit update(
            @PathVariable Long id,
            @RequestParam String name) {

        return unitService.updateUnitName(id, name);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        unitService.deleteUnit(id);
    }
}
