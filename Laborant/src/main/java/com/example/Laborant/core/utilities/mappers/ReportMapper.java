package com.example.Laborant.core.utilities.mappers;

import org.springframework.stereotype.Component;

import com.example.Laborant.business.requests.CreateReportRequest;
import com.example.Laborant.business.responses.GetLaborantByIdResponse;
import com.example.Laborant.business.responses.GetPatientByIdResponse;
import com.example.Laborant.entities.concreates.Laborant;
import com.example.Laborant.entities.concreates.Patient;
import com.example.Laborant.entities.concreates.Report;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ReportMapper {
    private ModelMapperService modelMapperService;

    public Laborant mapToLaborant(GetLaborantByIdResponse laborantDto) {
        return modelMapperService.forResponse().map(laborantDto, Laborant.class);
    }

    public Patient mapToPatient(GetPatientByIdResponse patientDto) {
        return modelMapperService.forResponse().map(patientDto, Patient.class);
    }

    public Report mapToReport(CreateReportRequest createReportRequest) {
        return modelMapperService.forResponse().map(createReportRequest, Report.class);
    }
}
