package com.testcode.masterservice.module.unit.service;

import com.testcode.masterservice.application.basepayload.BaseResponse;
import com.testcode.masterservice.module.unit.payload.UnitRequest;
import com.testcode.masterservice.module.unit.payload.UnitResponse;

import java.util.List;

public interface UnitService {

    BaseResponse<List<UnitResponse>> getAll();

    BaseResponse<UnitResponse> save(UnitRequest request);

    BaseResponse<UnitResponse> getOne(Long unitId);

    BaseResponse<UnitResponse> update(Long unitId, UnitRequest request);

    BaseResponse<UnitResponse> delete(Long unitId);
}
