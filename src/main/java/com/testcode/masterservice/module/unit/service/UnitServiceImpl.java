package com.testcode.masterservice.module.unit.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.testcode.masterservice.application.basepayload.BaseResponse;
import com.testcode.masterservice.application.exception.DataNotExistException;
import com.testcode.masterservice.application.exception.GeneralValidationException;
import com.testcode.masterservice.application.util.Mapper;
import com.testcode.masterservice.entity.Unit;
import com.testcode.masterservice.module.unit.payload.UnitRequest;
import com.testcode.masterservice.module.unit.payload.UnitResponse;
import com.testcode.masterservice.repository.UnitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UnitServiceImpl implements UnitService{

    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private Mapper mapper;

    @Override
    public BaseResponse<List<UnitResponse>> getAll() {
        // find all unit in database
        List<Unit> units = unitRepository.findAll();

        // map list of unit from database to list of response
        TypeReference<List<UnitResponse>> typeRef = new TypeReference<List<UnitResponse>>() {};
        List<UnitResponse> response = mapper.parseTypeRef(typeRef, units);

        return new BaseResponse<List<UnitResponse>>(response);
    }

    @Override
    public BaseResponse<UnitResponse> save(UnitRequest request) {

        // check for request
        if (!StringUtils.hasLength(request.getName())) {
            String error = "Unit name cannot be empty";
            log.error(error);
            throw new GeneralValidationException(error);
        }

        // map request to unit
        TypeReference<Unit> unitTypeRef = new TypeReference<Unit>() {};
        Unit unit = mapper.parseTypeRef(unitTypeRef, request);
        unit.setAssigned(Boolean.FALSE);
        // save unit entity
        unit = unitRepository.save(unit);

        // map unit to unit response
        TypeReference<UnitResponse> unitResponseTypeRef = new TypeReference<UnitResponse>() {};
        UnitResponse unitResponse = mapper.parseTypeRef(unitResponseTypeRef, unit);

        return new BaseResponse<UnitResponse>(unitResponse);
    }

    @Override
    public BaseResponse<UnitResponse> getOne(Long unitId) {
        // find by id
        Optional<Unit> optUnit = unitRepository.findById(unitId);

        // check if id exist
        if (!optUnit.isPresent()) {
            String error = "Unit not found";
            log.error(error);
            throw new DataNotExistException();
        }

        TypeReference<UnitResponse> unitResponseTypeRef = new TypeReference<UnitResponse>() {};
        UnitResponse unitResponse = mapper.parseTypeRef(unitResponseTypeRef, optUnit.get());

        return new BaseResponse<UnitResponse>(unitResponse);
    }

    @Override
    public BaseResponse<UnitResponse> update(Long unitId, UnitRequest request) {

        // check for request
        if (!StringUtils.hasLength(request.getName())) {
            String error = "Unit name cannot be empty";
            log.error(error);
            throw new GeneralValidationException(error);
        }

        // find by id
        Optional<Unit> optUnit = unitRepository.findById(unitId);

        // check if id exist
        if (!optUnit.isPresent()) {
            String error = "Unit not found";
            log.error(error);
            throw new DataNotExistException();
        }

        // map updated unit
        TypeReference<Unit> unitTypeRef = new TypeReference<Unit>() {};
        Unit updatedUnit = mapper.parseTypeRef(unitTypeRef, request);
        updatedUnit.setId(optUnit.get().getId());

        unitRepository.save(updatedUnit);

        // map unit to unit response
        TypeReference<UnitResponse> unitResponseTypeRef = new TypeReference<UnitResponse>() {};
        UnitResponse unitResponse = mapper.parseTypeRef(unitResponseTypeRef, updatedUnit);

        return new BaseResponse<UnitResponse>(unitResponse);
    }

    @Override
    public BaseResponse<UnitResponse> delete(Long unitId) {
        // find by id
        Optional<Unit> optUnit = unitRepository.findById(unitId);

        // check if id exist
        if (!optUnit.isPresent()) {
            String error = "Unit not found";
            log.error(error);
            throw new DataNotExistException();
        }

        unitRepository.deleteById(unitId);
        return null;
    }
}
