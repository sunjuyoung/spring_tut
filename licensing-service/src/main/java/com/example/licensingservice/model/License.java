package com.example.licensingservice.model;

import jdk.jfr.DataAmount;
import lombok.Data;

@Data
public class License {

    private int id;
    private String licenseId;
    private String description;
    private String organizationId;
    private String productName;
    private String licenseType;
}
