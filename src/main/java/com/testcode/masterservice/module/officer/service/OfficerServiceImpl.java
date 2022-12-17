package com.testcode.masterservice.module.officer.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.testcode.masterservice.application.basepayload.BaseResponse;
import com.testcode.masterservice.application.exception.DataNotExistException;
import com.testcode.masterservice.application.exception.GeneralValidationException;
import com.testcode.masterservice.application.util.Mapper;
import com.testcode.masterservice.entity.Officer;
import com.testcode.masterservice.module.officer.payload.OfficerRequest;
import com.testcode.masterservice.module.officer.payload.OfficerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.testcode.masterservice.repository.OfficerRepository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class OfficerServiceImpl implements OfficerService{

    @Autowired
    private OfficerRepository officerRepository;

    @Autowired
    private Mapper mapper;


    @Override
    public BaseResponse<List<OfficerResponse>> getAll() {
        // find all officer in database
        List<Officer> officers = officerRepository.findAll();

        // map list of officer from database to list of response
        TypeReference<List<OfficerResponse>> typeRef = new TypeReference<List<OfficerResponse>>() {};
        List<OfficerResponse> response = mapper.parseTypeRef(typeRef, officers);

        return new BaseResponse<List<OfficerResponse>>(response);
    }

    @Override
    public BaseResponse<OfficerResponse> save(OfficerRequest request) {

        // check for request
        if (!StringUtils.hasLength(request.getName())) {
            String error = "Officer name cannot be empty";
            log.error(error);
            throw new GeneralValidationException(error);
        }

        // map request to officer
        TypeReference<Officer> officerTypeRef = new TypeReference<Officer>() {};
        Officer officer = mapper.parseTypeRef(officerTypeRef, request);
        officer.setAssigned(Boolean.FALSE);

        // save officer entity
        officer = officerRepository.save(officer);

        // map officer to officer response
        TypeReference<OfficerResponse> officerResponseTypeRef = new TypeReference<OfficerResponse>() {};
        OfficerResponse officerResponse = mapper.parseTypeRef(officerResponseTypeRef, officer);

        return new BaseResponse<OfficerResponse>(officerResponse);

    }

    @Override
    public BaseResponse<OfficerResponse> getOne(Integer officerId) {
        // find by id
        Optional<Officer> optOfficer = officerRepository.findById(officerId);

        // check if id exist
        if (!optOfficer.isPresent()) {
            String error = "Officer not found";
            log.error(error);
            throw new DataNotExistException();
        }

        // map officer to officer response
        TypeReference<OfficerResponse> officerResponseTypeRef = new TypeReference<OfficerResponse>() {};
        OfficerResponse officerResponse = mapper.parseTypeRef(officerResponseTypeRef, optOfficer.get());

        return new BaseResponse<OfficerResponse>(officerResponse);
    }

    @Override
    public BaseResponse<OfficerResponse> update(Integer officerId, OfficerRequest request) {

        // check for request
        if (!StringUtils.hasLength(request.getName())) {
            String error = "Officer name cannot be empty";
            log.error(error);
            throw new GeneralValidationException(error);
        }

        // find by id
        Optional<Officer> optOfficer = officerRepository.findById(officerId);

        // check if id exist
        if (!optOfficer.isPresent()) {
            String error = "Officer not found";
            log.error(error);
            throw new DataNotExistException();
        }

        // map updated officer
        TypeReference<Officer> officerTypeRef = new TypeReference<Officer>() {};
        Officer updatedOfficer = mapper.parseTypeRef(officerTypeRef, request);
        updatedOfficer.setId(optOfficer.get().getId());


        officerRepository.save(updatedOfficer);

        // map officer to officer response
        TypeReference<OfficerResponse> officerResponseTypeRef = new TypeReference<OfficerResponse>() {};
        OfficerResponse officerResponse = mapper.parseTypeRef(officerResponseTypeRef, updatedOfficer);

        return new BaseResponse<OfficerResponse>(officerResponse);
    }

    @Override
    public BaseResponse<OfficerResponse> delete(Integer officerId) {
        // find by id
        Optional<Officer> optOfficer = officerRepository.findById(officerId);

        // check if id exist
        if (!optOfficer.isPresent()) {
            String error = "Officer not found";
            log.error(error);
            throw new DataNotExistException();
        }

        officerRepository.deleteById(officerId);
        return null;
    }
}
