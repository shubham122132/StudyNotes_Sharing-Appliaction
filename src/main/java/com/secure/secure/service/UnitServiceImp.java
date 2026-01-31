package com.secure.secure.service;

import com.secure.secure.model.SubSubject;
import com.secure.secure.model.Unit;
import com.secure.secure.repository.SubSubjectRepository;
import com.secure.secure.repository.UnitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UnitServiceImp implements  UnitService{

    private final UnitRepository unitRepo;
    private final SubSubjectRepository subSubjectRepo;

    private static final String UPLOAD_DIR = "uploads/";


    @Override
    public Unit createUnit(String name, Long subSubjectId, MultipartFile pdf) throws IOException {

        SubSubject subSubject = subSubjectRepo.findById(subSubjectId)
                .orElseThrow(() -> new RuntimeException(
                        "SubSubject not found with id: " + subSubjectId));

        File dir = new File(UPLOAD_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String filePath = UPLOAD_DIR + System.currentTimeMillis()
                + "_" + pdf.getOriginalFilename();

        pdf.transferTo(new File(filePath));

        Unit unit = new Unit();
        unit.setName(name);
        unit.setPdfUrl(filePath);
        unit.setSubSubject(subSubject);

        return unitRepo.save(unit);
    }

    @Override
    public List<Unit> getAllUnits() {
        return unitRepo.findAll();
    }

    @Override
    public List<Unit> getUnitsBySubSubject(Long subSubjectId) {
        return unitRepo.findBySubSubjectId(subSubjectId);
    }

    @Override
    public Unit updateUnitName(Long unitId, String name) {

        Unit unit = unitRepo.findById(unitId)
                .orElseThrow(() -> new RuntimeException(
                        "Unit not found with id: " + unitId));

        unit.setName(name);
        return unitRepo.save(unit);
    }

    @Override
    public void deleteUnit(Long unitId) {

        if (!unitRepo.existsById(unitId)) {
            throw new RuntimeException("Unit not found with id: " + unitId);
        }

        unitRepo.deleteById(unitId);
    }

}
