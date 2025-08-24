package com.example.StockFlow.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompanyDTO {

    private String id;

    @NotBlank
    @Size(min = 3, max = 100)
    private String name;

    @NotBlank
    @Size(min = 10, max = 100)
    private String address;

    @NotBlank
    @Size(min = 10, max = 13)
    private String contactNo;

    @NotBlank
    @Email
    private String registeredEmail;

}
