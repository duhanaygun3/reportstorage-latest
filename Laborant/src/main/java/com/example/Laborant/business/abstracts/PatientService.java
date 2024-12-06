package com.example.Laborant.business.abstracts;

import java.util.List;

import com.example.Laborant.business.requests.CreatePatientRequest;
import com.example.Laborant.business.responses.GetAllPatientsResponse;
import com.example.Laborant.business.responses.GetPatientByIdResponse;

public interface PatientService {

    List<GetAllPatientsResponse> getAll();

    void add(CreatePatientRequest createPatientRequest);

    GetPatientByIdResponse getPatientById(int id);

    void delete(int id);

}
