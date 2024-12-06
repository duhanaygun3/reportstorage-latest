package com.example.Laborant.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLaborantRequest {

    private String firstName;
    private String lastName;
    private String hospitalIdentityNo;
}
