package com.testcode.masterservice.module.camera.service;

import com.testcode.masterservice.application.basepayload.BaseResponse;
import com.testcode.masterservice.entity.Camera;
import com.testcode.masterservice.entity.Unit;
import com.testcode.masterservice.application.exception.DataNotExistException;
import com.testcode.masterservice.application.exception.GeneralValidationException;
import com.testcode.masterservice.module.camera.payload.CameraRequest;
import com.testcode.masterservice.module.camera.payload.CameraResponse;
import com.testcode.masterservice.repository.CameraRepository;
import com.testcode.masterservice.repository.UnitRepository;
import com.testcode.masterservice.application.util.Mapper;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class CameraServiceImpl implements CameraService{

    @Autowired
    private CameraRepository cameraRepository;

    @Autowired
    private Mapper mapper;

    @Autowired
    private UnitRepository unitRepository;


    @Override
    public BaseResponse<List<CameraResponse>> getAll() {

        // find all camera in database
        List<Camera> cameras = cameraRepository.findAll();

        // map list of camera from database to list of response
        TypeReference<List<CameraResponse>> typeRef = new TypeReference<List<CameraResponse>>() {};
        List<CameraResponse> response = mapper.parseTypeRef(typeRef, cameras);

        return new BaseResponse<List<CameraResponse>>(response);
    }

    @Override
    public BaseResponse<CameraResponse> save(CameraRequest request) {

        // check for request
        if (!StringUtils.hasLength(request.getName())) {
            String error = "Camera name cannot be empty";
            log.error(error);
            throw new GeneralValidationException(error);
        }

        Optional<Unit> optUnit = null;
        if(!Objects.isNull(request.getUnitId())){
            optUnit = unitRepository.findById(request.getUnitId());
        }

        // map request to camera
        TypeReference<Camera> cameraTypeRef = new TypeReference<Camera>() {};
        Camera camera = mapper.parseTypeRef(cameraTypeRef, request);
        camera.setAddDate(LocalDateTime.now());

        if(!Objects.isNull(optUnit)){
            camera.setUnit(optUnit.get());
        }

        // save camera entity
        camera = cameraRepository.save(camera);

        // map camera to camera response
        TypeReference<CameraResponse> cameraResponseTypeRef = new TypeReference<CameraResponse>() {};
        CameraResponse cameraResponse = mapper.parseTypeRef(cameraResponseTypeRef, camera);

        return new BaseResponse<CameraResponse>(cameraResponse);
    }

    @Override
    public BaseResponse<CameraResponse> getOne(Integer cameraId) {

        // find by id
        Optional<Camera> optCamera = cameraRepository.findById(cameraId);

        // check if id exist
        if (!optCamera.isPresent()) {
            String error = "Camera not found";
            log.error(error);
            throw new DataNotExistException();
        }

        // map camera to camera response
        TypeReference<CameraResponse> cameraResponseTypeRef = new TypeReference<CameraResponse>() {};
        CameraResponse cameraResponse = mapper.parseTypeRef(cameraResponseTypeRef, optCamera.get());

        return new BaseResponse<CameraResponse>(cameraResponse);
    }

    @Override
    public BaseResponse<CameraResponse> update(Integer cameraId, CameraRequest request) {

        // check for request
        if (!StringUtils.hasLength(request.getName())) {
            String error = "Camera name cannot be empty";
            log.error(error);
            throw new GeneralValidationException(error);
        }

        // find by id
        Optional<Camera> optCamera = cameraRepository.findById(cameraId);

        // check if id exist
        if (!optCamera.isPresent()) {
            String error = "Camera not found";
            log.error(error);
            throw new DataNotExistException();
        }

        Optional<Unit> optUnit = null;
        if(!Objects.isNull(request.getUnitId())){
            optUnit = unitRepository.findById(request.getUnitId());
        }

        // map updated camera
        TypeReference<Camera> cameraTypeRef = new TypeReference<Camera>() {};
        Camera updatedCamera = mapper.parseTypeRef(cameraTypeRef, request);
        updatedCamera.setId(optCamera.get().getId());
        updatedCamera.setAddDate(optCamera.get().getAddDate());

        if(!Objects.isNull(optUnit)){
            updatedCamera.setUnit(optUnit.get());
        }

        cameraRepository.save(updatedCamera);

        // map camera to camera response
        TypeReference<CameraResponse> cameraResponseTypeRef = new TypeReference<CameraResponse>() {};
        CameraResponse cameraResponse = mapper.parseTypeRef(cameraResponseTypeRef, updatedCamera);

        return new BaseResponse<CameraResponse>(cameraResponse);
    }

    @Override
    public BaseResponse<CameraResponse> delete(Integer cameraId) {

        // find by id
        Optional<Camera> optCamera = cameraRepository.findById(cameraId);

        // check if id exist
        if (!optCamera.isPresent()) {
            String error = "Camera not found";
            log.error(error);
            throw new DataNotExistException();
        }

        cameraRepository.deleteById(cameraId);
        return null;
    }


}
