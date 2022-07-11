package com.example.organizationservice.repository;

import com.example.organizationservice.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization,String> {
    public Optional<Organization> findById(String organizationId);
}
