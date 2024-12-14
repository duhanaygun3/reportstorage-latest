package com.example.Laborant.business.requests;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateReportRequest {

    private String diagnostic;

    private String diagnosticDetail;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate reportDate;

    // private byte[] photo;

    private int patient_id;

    // private Patient patient;

    private int laborant_id;

    // private Laborant laborant;

    public void setReportDate(String dateString) {
        this.reportDate = LocalDate.parse(dateString); // String formatında gelen tarihi LocalDate'e çeviriyoruz
    }

}
