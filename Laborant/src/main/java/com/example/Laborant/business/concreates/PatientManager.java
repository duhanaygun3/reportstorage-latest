package com.example.Laborant.business.concreates;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.Laborant.business.abstracts.PatientService;
import com.example.Laborant.business.requests.CreatePatientRequest;
import com.example.Laborant.business.responses.GetAllPatientsResponse;
import com.example.Laborant.business.responses.GetPatientByIdResponse;
import com.example.Laborant.core.utilities.mappers.ModelMapperService;
import com.example.Laborant.dataAccess.abstracts.PatientRepository;
import com.example.Laborant.entities.concreates.Patient;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PatientManager implements PatientService {

    private PatientRepository patientRepository;

    private ModelMapperService modelMapperService;

    @Override
    public void add(CreatePatientRequest createPatientRequest) {

        Patient patient = this.modelMapperService.forRequest().map(createPatientRequest, Patient.class);

        this.patientRepository.save(patient);

    }

    @Override
    public List<GetAllPatientsResponse> getAll() {

        List<Patient> patients = patientRepository.findAll();

        List<GetAllPatientsResponse> getAllPatientsResponses = patients.stream()
                .map(patient -> modelMapperService.forResponse().map(patient, GetAllPatientsResponse.class))
                .collect(Collectors.toList());

        return getAllPatientsResponses;
    }

    @Override
    public GetPatientByIdResponse getPatientById(int id) {

        Patient patient = patientRepository.findById(id).orElseThrow();

        GetPatientByIdResponse getPatientByIdResponse = this.modelMapperService.forResponse().map(patient,
                GetPatientByIdResponse.class);

        return getPatientByIdResponse;
    }

    @Override
    public void delete(int id) {

        patientRepository.deleteById(id);
    }

}
