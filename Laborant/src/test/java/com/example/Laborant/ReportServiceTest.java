package com.example.Laborant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.Times;
import org.modelmapper.ModelMapper;

import com.example.Laborant.business.concreates.ReportFacede;
import com.example.Laborant.business.concreates.ReportManager;
import com.example.Laborant.business.requests.CreateReportRequest;
import com.example.Laborant.business.responses.GetLaborantByIdResponse;
import com.example.Laborant.business.responses.GetPatientByIdResponse;
import com.example.Laborant.business.responses.GetReportByIdResponse;
import com.example.Laborant.core.utilities.mappers.ModelMapperManager;
import com.example.Laborant.core.utilities.mappers.ModelMapperService;
import com.example.Laborant.core.utilities.mappers.ReportMapper;
import com.example.Laborant.dataAccess.abstracts.ReportRepository;
import com.example.Laborant.entities.concreates.Laborant;
import com.example.Laborant.entities.concreates.Patient;
import com.example.Laborant.entities.concreates.Report;

public class ReportServiceTest {

    private ReportManager reportManager;

    private ReportRepository reportRepository;
    private ReportFacede reportFacede;
    private ReportMapper reportMapper;
    private ModelMapperService modelMapperService;

    @BeforeEach
    public void setUp() {
        reportRepository = Mockito.mock(ReportRepository.class);
        reportFacede = Mockito.mock(ReportFacede.class);
        reportMapper = Mockito.mock(ReportMapper.class);

        ModelMapper modelMapper = new ModelMapper();
        modelMapperService = new ModelMapperManager(modelMapper);

        reportManager = new ReportManager(reportRepository, reportFacede, reportMapper, modelMapperService);

    }

    @DisplayName("should Return GetReportByIdResponse when given Id Match With Report")
    @Test
    // Name Test
    void shouldReturnGetReportByIdResponse_whenIdMatchWithReport() {

        // Set Test veriable
        int id = 1;

        Laborant laborant = new Laborant();
        laborant.setFirstName("Duhan");
        laborant.setLastName("aygun");
        laborant.setHospitalIdentityNo("1234567");
        laborant.setId(1);

        Patient patient = new Patient();
        patient.setFirstName("Ayse");
        patient.setLastName("yavas");
        patient.setPatientNo("1234");
        patient.setId(2);

        Report report = new Report();
        report.setId(1);
        report.setDiagnostic("Agri");
        report.setDiagnosticDetail("karin agrisi");
        report.setLaborant(laborant);
        report.setPatient(patient);

        GetReportByIdResponse getReportByIdResponseExpected = new GetReportByIdResponse();
        getReportByIdResponseExpected.setDiagnostic("Agri");
        getReportByIdResponseExpected.setDiagnosticDetail("karin agrisi");
        getReportByIdResponseExpected.setLaborantFirstName("Duhan");
        getReportByIdResponseExpected.setLaborantLastName("aygun");
        getReportByIdResponseExpected.setPatientFirstName("Ayse");
        getReportByIdResponseExpected.setPatientLastName("yavas");

        // Determine service behaviors
        Mockito.when(reportRepository.findById(id)).thenReturn(Optional.of(report));

        // Execute metod that will be tested
        GetReportByIdResponse result = reportManager.getReportById(id);

        // Compare test results
        assertEquals(getReportByIdResponseExpected, result);

        // check operation of dependent services
        verify(reportRepository, new Times(1)).findById(id);

    }

    @DisplayName("should Throw ReportNowFoundExeption when given Id Not Match With Report")
    @Test
    // Name Test
    void shouldThrowReportNowFoundExeption_whenIdNotMatchWithReport() {

        // Set Test veriable
        int id = 5;

        // Determine service behaviors
        Mockito.when(reportRepository.findById(id)).thenReturn(Optional.empty());

        // Compare test results
        Assertions.assertThatThrownBy(() -> reportManager.getReportById(id))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("Report not found with ID: ");

        // check operation of dependent services
        verify(reportRepository, new Times(1)).findById(id);

    }

    @DisplayName("add New Report Succesfull when createReportRequest given")
    @Test
    void addNewReportSuccesfull_whencreateReportRequestgiven() {

        CreateReportRequest createReportRequest = new CreateReportRequest();
        createReportRequest.setDiagnostic("Teshis");
        createReportRequest.setDiagnosticDetail("teshis detayi.");
        createReportRequest.setReportDate("2024-12-06");
        createReportRequest.setLaborant_id(1);
        createReportRequest.setPatient_id(2);

        GetLaborantByIdResponse getLaborantByIdResponse = new GetLaborantByIdResponse();
        getLaborantByIdResponse.setFirstName("Duhan");
        getLaborantByIdResponse.setLastName("aygun");
        getLaborantByIdResponse.setHospitalIdentityNo("1234567");
        getLaborantByIdResponse.setId(1);

        GetPatientByIdResponse getpatientByIdResponse = new GetPatientByIdResponse();
        getpatientByIdResponse.setFirstName("Ayse");
        getpatientByIdResponse.setLastName("yavas");
        getpatientByIdResponse.setPatientNo("1234");
        getpatientByIdResponse.setId(2);

        Laborant laborant = new Laborant();
        laborant.setFirstName("Duhan");
        laborant.setLastName("aygun");
        laborant.setHospitalIdentityNo("1234567");
        laborant.setId(1);

        Patient patient = new Patient();
        patient.setFirstName("Ayse");
        patient.setLastName("yavas");
        patient.setPatientNo("1234");
        patient.setId(2);

        Report report = new Report();
        report.setId(1);
        report.setDiagnostic("Agri");
        report.setDiagnosticDetail("karin agrisi");
        report.setLaborant(laborant);
        report.setPatient(patient);

        Mockito.when(reportFacede.getLaborantById(createReportRequest.getPatient_id()))
                .thenReturn(getLaborantByIdResponse);
        Mockito.when(reportFacede.getPatientById(createReportRequest.getPatient_id()))
                .thenReturn(getpatientByIdResponse);

        Mockito.when(reportMapper.mapToLaborant(getLaborantByIdResponse)).thenReturn(laborant);
        Mockito.when(reportMapper.mapToPatient(getpatientByIdResponse)).thenReturn(patient);
        Mockito.when(reportMapper.mapToReport(createReportRequest)).thenReturn(report);

        reportManager.add(createReportRequest);

        // Compare test results
        verify(reportRepository, new Times(1)).save(any(Report.class));

    }

}
