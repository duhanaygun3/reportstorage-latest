package com.example.Laborant.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPatientByIdResponse {

    private int id;
    private String firstName;
    private String lastName;
    private String patientNo;
}
