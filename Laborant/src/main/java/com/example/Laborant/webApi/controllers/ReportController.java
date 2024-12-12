package com.example.Laborant.webApi.controllers;

import java.sql.Date;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.Laborant.business.abstracts.ReportService;
import com.example.Laborant.business.requests.CreateReportRequest;
import com.example.Laborant.business.requests.UpdateReportRequest;
import com.example.Laborant.business.responses.GetAllReportsResponse;
import com.example.Laborant.business.responses.GetReportByIdResponse;
import com.example.Laborant.core.utilities.mappers.ModelMapperService;
import com.example.Laborant.entities.concreates.Laborant;
import com.example.Laborant.entities.concreates.Patient;
import com.example.Laborant.entities.concreates.Report;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/reports")
@AllArgsConstructor
public class ReportController {
    private ModelMapperService modelMapperService;
    private ReportService reportService;

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody CreateReportRequest createReportRequest) {
        reportService.add(createReportRequest);
    }

    @GetMapping("/getall")
    public List<GetAllReportsResponse> getAll() {
        return reportService.getAllReports();
    }

    @GetMapping("/test")
    public GetAllReportsResponse testMapping() {
        // Örnek bir Report nesnesi oluşturun
        Patient patient = new Patient();
        patient.setFirstName("Ahmet");
        patient.setLastName("Yılmaz");

        Laborant laborant = new Laborant();
        laborant.setFirstName("Mehmet");
        laborant.setLastName("Demir");

        Report report = new Report();
        report.setId(6);
        report.setPatient(patient);
        report.setLaborant(laborant);
        report.setDiagnostic("Grip");
        report.setDiagnosticDetail("Yüksek ateş");
        report.setReportDate(Date.valueOf("2024-11-28"));

        // ModelMapper ile dönüşümü test edin
        return modelMapperService.forResponse().map(report, GetAllReportsResponse.class);
    }

    @PatchMapping("/edit")
    void update(
            @RequestBody UpdateReportRequest updateReportRequest) {

        reportService.update(updateReportRequest);

    }

    @GetMapping("/search/patient")
    public List<GetAllReportsResponse> searchByPatient(

            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String patientNo) {
        return reportService.searchByPatient(firstName, lastName, patientNo);
    }

    @GetMapping("/search/laborant")
    public List<GetAllReportsResponse> searchByLaborant(

            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName) {
        return reportService.searchByLaborant(firstName, lastName);
    }

    @GetMapping("/search/sorted")
    public List<GetAllReportsResponse> getAllReportsSorted(@RequestParam(defaultValue = "desc") String sortDirection) {
        return reportService.getAllReportsSorted(sortDirection);

    }
    // Artan sıralama endpoint'i

    @GetMapping("/asc")
    public List<GetAllReportsResponse> getReportsSortedAscending() {
        List<GetAllReportsResponse> reports = reportService.getAllReportsSorted(true);
        return reports;
    }

    // Azalan sıralama endpoint'i
    @GetMapping("/desc")
    public List<GetAllReportsResponse> getReportsSortedDescending() {
        List<GetAllReportsResponse> reports = reportService.getAllReportsSorted(false);
        return reports;
    }

    @GetMapping("/{id}")
    public GetReportByIdResponse getReportById(@PathVariable int id) {
        return reportService.getReportById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable int id) {
        reportService.delete(id);
    }

}
