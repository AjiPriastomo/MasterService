package com.testcode.masterservice.module.camera.controller;

import com.testcode.masterservice.application.basepayload.BaseResponse;
import com.testcode.masterservice.module.camera.payload.CameraRequest;
import com.testcode.masterservice.module.camera.payload.CameraResponse;
import com.testcode.masterservice.module.camera.service.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "camera")
@ResponseStatus(HttpStatus.OK)
public class CameraController {

    @Autowired
    private CameraService cameraService;

    @GetMapping
    BaseResponse<List<CameraResponse>> getAll() {
        return cameraService.getAll();
    }

    @PostMapping
    BaseResponse<CameraResponse> save(@RequestBody CameraRequest request) {
        return cameraService.save(request);
    }

    @GetMapping("{cameraId}")
    BaseResponse<CameraResponse> getOne(@PathVariable("cameraId") Integer cameraId) {
        return cameraService.getOne(cameraId);
    }

    @PutMapping("{cameraId}")
    BaseResponse<CameraResponse> update(@PathVariable("cameraId") Integer cameraId,
                                        @RequestBody CameraRequest request) {
        return cameraService.update(cameraId, request);
    }

    @DeleteMapping("{cameraId}")
    BaseResponse<CameraResponse> delete(@PathVariable("cameraId") Integer cameraId) {
        return cameraService.delete(cameraId);
    }


}
