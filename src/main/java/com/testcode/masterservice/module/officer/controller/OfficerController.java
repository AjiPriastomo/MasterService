package com.testcode.masterservice.module.officer.controller;

import com.testcode.masterservice.application.basepayload.BaseResponse;
import com.testcode.masterservice.module.officer.payload.OfficerRequest;
import com.testcode.masterservice.module.officer.payload.OfficerResponse;
import com.testcode.masterservice.module.officer.service.OfficerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "officer")
@ResponseStatus(HttpStatus.OK)
public class OfficerController {

    @Autowired
    private OfficerService officerService;

    @GetMapping
    BaseResponse<List<OfficerResponse>> getAll() {
        return officerService.getAll();
    }


    @PostMapping
    BaseResponse<OfficerResponse> save(@RequestBody OfficerRequest request) {
        return officerService.save(request);
    }

    @GetMapping("{officerId}")
    BaseResponse<OfficerResponse> getOne(@PathVariable("officerId") Integer officerId) {
        return officerService.getOne(officerId);
    }

    @PutMapping("{officerId}")
    BaseResponse<OfficerResponse> update(@PathVariable("officerId") Integer officerId,
                                         @RequestBody OfficerRequest request) {
        return officerService.update(officerId, request);
    }

    @DeleteMapping("{officerId}")
    BaseResponse<OfficerResponse> delete(@PathVariable("officerId") Integer officerId) {
        return officerService.delete(officerId);
    }
}
