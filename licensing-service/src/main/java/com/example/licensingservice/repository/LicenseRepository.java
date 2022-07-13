package com.example.licensingservice.repository;

import com.example.licensingservice.model.License;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LicenseRepository extends JpaRepository<License,String> {
    public List<License> findByOrOrganizationId(String organizationId);
    public License findByOrOrganizationIdAndLicenseId(String organizationId, String licenseId);
}
