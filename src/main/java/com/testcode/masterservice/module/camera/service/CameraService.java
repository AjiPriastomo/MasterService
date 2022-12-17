package com.testcode.masterservice.module.camera.service;

import com.testcode.masterservice.application.basepayload.BaseResponse;
import com.testcode.masterservice.module.camera.payload.CameraRequest;
import com.testcode.masterservice.module.camera.payload.CameraResponse;

import java.util.List;

public interface CameraService {

    BaseResponse<List<CameraResponse>> getAll();

    BaseResponse<CameraResponse> save(CameraRequest request);

    BaseResponse<CameraResponse> getOne(Integer cameraId);

    BaseResponse<CameraResponse> update(Integer cameraId, CameraRequest request);

    BaseResponse<CameraResponse> delete(Integer cameraId);
}
