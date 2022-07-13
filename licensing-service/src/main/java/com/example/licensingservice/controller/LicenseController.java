package com.example.licensingservice.controller;

import com.example.licensingservice.model.License;
import com.example.licensingservice.service.LicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "v1/organization/{organizationId}/license")
@RequiredArgsConstructor
public class LicenseController {

    private final LicenseService licenseService;

    @GetMapping(value = "/{licenseId}/{clientType}")
    public License getLicenseWithClient(@PathVariable("licenseId")String licenseId,
                                                        @PathVariable("organizationId")String organizationId,
                                                        @PathVariable("clientType")String clientType){

        License license = licenseService.getLicense(licenseId, organizationId,clientType);

        return license;
    }

    @GetMapping(value = "/{licenseId}")
    public ResponseEntity<License> getLicense(@PathVariable("licenseId")String licenseId,
                                              @PathVariable("organizationId")String organizationId){

        License license = licenseService.getLicense(licenseId, organizationId);
        license.add(
                linkTo(methodOn(LicenseController.class).getLicense(organizationId, license.getLicenseId())).withSelfRel(),
                linkTo(methodOn(LicenseController.class).createLicense(license, organizationId, null)).withRel("createLicense"));
        return ResponseEntity.ok(license);
    }

    @PostMapping
    public ResponseEntity<License> createLicense(@RequestBody License license,
                                                 @PathVariable("organizationId")String organizationId,
                                                 @RequestHeader(value = "Accept-Language", required = false) Locale locale){
        License license1 = licenseService.createLicense(license, organizationId, locale);
        return ResponseEntity.ok(license1);
    }

    @PutMapping
    public ResponseEntity<License> updateLicense(@PathVariable("organizationId") String organizationId, @RequestBody License request) {
        return ResponseEntity.ok(licenseService.updateLicense(request));
    }

    @DeleteMapping(value="/{licenseId}")
    public ResponseEntity<String> deleteLicense(@PathVariable("organizationId") String organizationId, @PathVariable("licenseId") String licenseId) {
        return ResponseEntity.ok(licenseService.deleteLicense(licenseId));
    }

}
