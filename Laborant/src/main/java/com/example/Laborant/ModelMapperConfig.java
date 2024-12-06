package com.example.Laborant;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.Laborant.business.requests.UpdateReportRequest;
import com.example.Laborant.business.responses.GetAllReportsResponse;
import com.example.Laborant.business.responses.GetReportByIdResponse;
import com.example.Laborant.entities.concreates.Report;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // UpdateReportRequest -> Report eşlemeleri
        //
        modelMapper.addMappings(new PropertyMap<UpdateReportRequest, Report>() {
            @Override
            protected void configure() {
                skip(destination.getLaborant()); // Laborant alanını skip et
                skip(destination.getPatient()); // Patient alanını skip et
            }
        });

        // Custom Mapping: Report -> GetAllReportsResponse
        modelMapper.typeMap(Report.class, GetAllReportsResponse.class).addMappings(mapper -> {
            mapper.map(src -> src.getPatient().getFirstName(), GetAllReportsResponse::setPatientFirstName);
            mapper.map(src -> src.getPatient().getLastName(), GetAllReportsResponse::setPatientLastName);
            mapper.map(src -> src.getLaborant().getFirstName(), GetAllReportsResponse::setLaborantFirstName);
            mapper.map(src -> src.getLaborant().getLastName(), GetAllReportsResponse::setLaborantLastName);

        });

        modelMapper.typeMap(Report.class, GetReportByIdResponse.class).addMappings(mapper -> {
            mapper.map(src -> src.getPatient().getFirstName(), GetReportByIdResponse::setPatientFirstName);
            mapper.map(src -> src.getPatient().getLastName(), GetReportByIdResponse::setPatientLastName);
            mapper.map(src -> src.getLaborant().getFirstName(), GetReportByIdResponse::setLaborantFirstName);
            mapper.map(src -> src.getLaborant().getLastName(), GetReportByIdResponse::setLaborantLastName);

        });

        return modelMapper;
    }
}
