package com.example.Laborant.webApi.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.Laborant.business.abstracts.PatientService;
import com.example.Laborant.business.requests.CreatePatientRequest;
import com.example.Laborant.business.responses.GetAllPatientsResponse;
import com.example.Laborant.business.responses.GetPatientByIdResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/patients")
@AllArgsConstructor
public class PatientController {

    private PatientService patientService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody CreatePatientRequest createPatientRequest) {
        patientService.add(createPatientRequest);
    }

    @GetMapping
    public List<GetAllPatientsResponse> getAll() {

        return patientService.getAll();
    }

    @GetMapping("/{id}")
    public GetPatientByIdResponse getPatientById(int id) {
        return patientService.getPatientById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(int id) {
        patientService.delete(id);
    }

}
