package com.testcode.masterservice.module.unit.controller;

import com.testcode.masterservice.application.basepayload.BaseResponse;
import com.testcode.masterservice.module.unit.payload.UnitRequest;
import com.testcode.masterservice.module.unit.payload.UnitResponse;
import com.testcode.masterservice.module.unit.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "unit")
@ResponseStatus(HttpStatus.OK)
public class UnitController {

    @Autowired
    private UnitService unitService;

    @GetMapping
    BaseResponse<List<UnitResponse>> getAll() {
        return unitService.getAll();
    }

    @PostMapping
    BaseResponse<UnitResponse> save(@RequestBody UnitRequest request) {
        return unitService.save(request);
    }

    @GetMapping("{unitId}")
    BaseResponse<UnitResponse> getOne(@PathVariable("unitId") Long unitId) {
        return unitService.getOne(unitId);
    }

    @PutMapping("{unitId}")
    BaseResponse<UnitResponse> update(@PathVariable("unitId") Long unitId,
                                      @RequestBody UnitRequest request) {
        return unitService.update(unitId, request);
    }

    @DeleteMapping("{unitId}")
    BaseResponse<UnitResponse> delete(@PathVariable("unitId") Long unitId) {
        return unitService.delete(unitId);
    }
}
