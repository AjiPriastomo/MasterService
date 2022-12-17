package com.testcode.masterservice.module.officer.service;

import com.testcode.masterservice.application.basepayload.BaseResponse;
import com.testcode.masterservice.module.officer.payload.OfficerRequest;
import com.testcode.masterservice.module.officer.payload.OfficerResponse;

import java.util.List;

public interface OfficerService {

    BaseResponse<List<OfficerResponse>> getAll();

    BaseResponse<OfficerResponse> save(OfficerRequest request);

    BaseResponse<OfficerResponse> getOne(Integer officerId);

    BaseResponse<OfficerResponse> update(Integer officerId, OfficerRequest request);

    BaseResponse<OfficerResponse> delete(Integer officerId);
}
