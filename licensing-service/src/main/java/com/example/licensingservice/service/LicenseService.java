package com.example.licensingservice.service;

import com.example.licensingservice.config.ServiceConfig;
import com.example.licensingservice.model.License;
import com.example.licensingservice.repository.LicenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LicenseService {

    @Autowired
    MessageSource messageSource;

    private final ServiceConfig config;

    private final LicenseRepository licenseRepository;


    public License getLicense(String licenseId, String organizationId, String clientType){
        License license = licenseRepository.findByOrOrganizationIdAndLicenseId(organizationId, licenseId);
        if(license == null){
            throw new IllegalStateException(
                    String.format(messageSource.getMessage(
                            "license.search.error.message",null,null ),
                            licenseId,organizationId));
        }

        return license;
    }

    public License getLicense(String licenseId, String organizationId){
        License license = licenseRepository.findByOrOrganizationIdAndLicenseId(organizationId, licenseId);
        if(license == null){
            throw new IllegalStateException(
                    String.format(messageSource.getMessage(
                            "license.search.error.message",null,null ),
                   licenseId,organizationId)
            );
        }
        return license;
    }

    public License createLicense(License license, String organizationId,Locale locale){
       license.setLicenseId(UUID.randomUUID().toString());
       licenseRepository.save(license);
       return license.withComment(config.getProperty());
    }

    public License updateLicense(License license){
        licenseRepository.save(license);
        return license.withComment(config.getProperty());
    }

    public String deleteLicense(String licenseId){
        String responseMessage = null;
        License license = new License();
        license.setLicenseId(licenseId);
        licenseRepository.delete(license);
        responseMessage = String.format(messageSource.getMessage("license.delete.message", null, null),licenseId);
        return responseMessage;

    }
}
