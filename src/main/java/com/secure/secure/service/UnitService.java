package com.secure.secure.service;

import com.secure.secure.model.Unit;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface UnitService {
    Unit createUnit(String name, Long subSubjectId, MultipartFile pdf) throws IOException;

    List<Unit> getAllUnits();

    List<Unit> getUnitsBySubSubject(Long subSubjectId);

    Unit updateUnitName(Long unitId, String name);

    void deleteUnit(Long unitId);
}
