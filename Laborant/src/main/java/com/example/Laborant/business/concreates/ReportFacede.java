package com.example.Laborant.business.concreates;

import org.springframework.stereotype.Service;

import com.example.Laborant.business.abstracts.LaborantService;
import com.example.Laborant.business.abstracts.PatientService;
import com.example.Laborant.business.responses.GetLaborantByIdResponse;
import com.example.Laborant.business.responses.GetPatientByIdResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportFacede {
    private final PatientService patientService;
    private final LaborantService laborantService;

    public GetLaborantByIdResponse getLaborantById(int id) {
        return laborantService.getLaborantById(id);
    }

    public GetPatientByIdResponse getPatientById(int id) {
        return patientService.getPatientById(id);
    }
}
