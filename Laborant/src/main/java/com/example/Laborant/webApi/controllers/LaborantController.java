package com.example.Laborant.webApi.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.Laborant.business.abstracts.LaborantService;
import com.example.Laborant.business.requests.CreateLaborantRequest;
import com.example.Laborant.business.responses.GetAllLaborantsResponse;
import com.example.Laborant.business.responses.GetLaborantByIdResponse;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/laborants")
@AllArgsConstructor
public class LaborantController {

    private LaborantService laborantService;

    @GetMapping()
    public List<GetAllLaborantsResponse> getLaborants() {
        return laborantService.getAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody CreateLaborantRequest createLaborantRequest) {
        laborantService.add(createLaborantRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteLaborant(@PathVariable int id) {
        laborantService.delete(id);

    }

    @GetMapping("/{id}")
    public GetLaborantByIdResponse getLaborantById(@PathVariable int id) {

        return laborantService.getLaborantById(id);
    }

}
