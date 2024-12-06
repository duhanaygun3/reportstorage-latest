package com.example.Laborant.business.requests;

import java.sql.Date;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateReportRequest {

    private String diagnostic;

    private String diagnosticDetail;

    private Date reportDate;

    // private byte[] photo;

    private int patient_id;

    // private Patient patient;

    private int laborant_id;

    // private Laborant laborant;

    public void setReportDate(String dateString) {
        LocalDate localDate = LocalDate.parse(dateString);
        this.reportDate = Date.valueOf(localDate);
    }

}
