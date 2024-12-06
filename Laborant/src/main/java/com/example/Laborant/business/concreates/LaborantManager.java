package com.example.Laborant.business.concreates;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.Laborant.business.abstracts.LaborantService;
import com.example.Laborant.business.requests.CreateLaborantRequest;
import com.example.Laborant.business.responses.GetAllLaborantsResponse;
import com.example.Laborant.business.responses.GetLaborantByIdResponse;
import com.example.Laborant.core.utilities.mappers.ModelMapperService;
import com.example.Laborant.dataAccess.abstracts.LaborantRepository;
import com.example.Laborant.entities.concreates.Laborant;

import lombok.AllArgsConstructor;

@Service // LaborantService interfaceninin ne olduğu belli değildir oyüzden bu sınıfın
         // service olarak işaretlenmesi gerekir
@AllArgsConstructor
public class LaborantManager implements LaborantService {

    private LaborantRepository laborantRepository;
    private ModelMapperService modelMapperService;

    @Override
    public List<GetAllLaborantsResponse> getAll() {

        List<Laborant> laborants = laborantRepository.findAll();

        List<GetAllLaborantsResponse> getAllLaborantsResponses = laborants.stream()
                .map(laborant -> modelMapperService.forResponse().map(laborant, GetAllLaborantsResponse.class))
                .collect(Collectors.toList());

        return getAllLaborantsResponses;
    }

    @Override
    public void add(CreateLaborantRequest createLaborantRequest) {

        Laborant laborant = this.modelMapperService.forRequest().map(createLaborantRequest, Laborant.class);

        this.laborantRepository.save(laborant);

    }

    @Override
    public void delete(int id) {

        laborantRepository.deleteById(id);
    }

    @Override
    public GetLaborantByIdResponse getLaborantById(int id) {

        Laborant laborant = laborantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Laborant with id " + id + " not found"));

        GetLaborantByIdResponse getLaborantByIdResponse = this.modelMapperService.forResponse().map(laborant,
                GetLaborantByIdResponse.class);

        return getLaborantByIdResponse;
    }

}
