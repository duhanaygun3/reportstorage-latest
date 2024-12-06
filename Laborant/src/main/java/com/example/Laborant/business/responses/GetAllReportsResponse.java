package com.example.Laborant.business.responses;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllReportsResponse {

    private String diagnostic;
    private String diagnosticDetail;
    private Date reportDate;

    private String patientFirstName; // Hasta adı
    private String patientLastName; // Hasta soyadı

    private String laborantFirstName; // Laborant adı
    private String laborantLastName; // Laborant soyadı

}
