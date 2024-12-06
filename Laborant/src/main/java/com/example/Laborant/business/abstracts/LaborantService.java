package com.example.Laborant.business.abstracts;

import java.util.List;

import com.example.Laborant.business.requests.CreateLaborantRequest;
import com.example.Laborant.business.responses.GetAllLaborantsResponse;
import com.example.Laborant.business.responses.GetLaborantByIdResponse;

public interface LaborantService {

    List<GetAllLaborantsResponse> getAll();

    void add(CreateLaborantRequest createLaborantRequest);

    void delete(int id);

    GetLaborantByIdResponse getLaborantById(int id);

}
