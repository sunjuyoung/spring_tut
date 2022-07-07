package com.example.licensingservice.controller;

import com.example.licensingservice.model.License;
import com.example.licensingservice.service.LicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping(value = "v1/organization/{organizationId}/license")
@RequiredArgsConstructor
public class LicenseController {

    private final LicenseService licenseService;

    @GetMapping(value = "/{licenseId}")
    public ResponseEntity<License> getLicense(@PathVariable("licenseId")String licenseId,
                                              @PathVariable("organizationId")String organizationId){

        License license = licenseService.getLicense(licenseId, organizationId);
        return ResponseEntity.ok(license);
    }

    @PostMapping
    public ResponseEntity<String> createLicense(@RequestBody License license,
                                                 @PathVariable("organizationId")String organizationId,
                                                 @RequestHeader(value = "Accept-Language", required = false) Locale locale){
        String license1 = licenseService.createLicense(license, organizationId, locale);
        return ResponseEntity.ok(license1);
    }



}
