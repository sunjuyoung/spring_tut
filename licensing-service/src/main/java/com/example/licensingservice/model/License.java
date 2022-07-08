package com.example.licensingservice.model;

import jdk.jfr.DataAmount;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class License extends RepresentationModel<License> {

    private int id;
    private String licenseId;
    private String description;
    private String organizationId;
    private String productName;
    private String licenseType;
}
