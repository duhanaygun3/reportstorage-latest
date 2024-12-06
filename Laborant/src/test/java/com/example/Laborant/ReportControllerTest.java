package com.example.Laborant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import com.example.Laborant.business.concreates.ReportManager;
import com.example.Laborant.business.responses.GetAllReportsResponse;
import com.example.Laborant.core.utilities.mappers.ModelMapperService;
import com.example.Laborant.dataAccess.abstracts.ReportRepository;
import com.example.Laborant.entities.concreates.Report;

@ExtendWith(MockitoExtension.class)
class ReportControllerTest {

    @Mock
    private ReportRepository reportRepository;

    @Mock
    private ModelMapperService modelMapperService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ReportManager reportManager;

    @Test
    void getAllReports_shouldReturnReportsSuccessfully() {
        // Report oluştur
        Report report = new Report();

        report.setDiagnostic("Test Diagnostic");

        // Rapor listesi oluştur
        List<Report> reportList = Arrays.asList(report);

        // GetAllReportsResponse oluştur
        GetAllReportsResponse mappedResponse = new GetAllReportsResponse();
        mappedResponse.setDiagnostic("Test Diagnostic");

        // Repository'nin findAll metodunu mock'la
        when(reportRepository.findAll()).thenReturn(reportList);

        // ModelMapperService'in forResponse metodunu mock'la
        when(modelMapperService.forResponse()).thenReturn(modelMapper);

        // Mapping işlemini mock'la
        when(modelMapper.map(report, GetAllReportsResponse.class))
                .thenReturn(mappedResponse);

        // Metodu çağır
        List<GetAllReportsResponse> result = reportManager.getAllReports();

        // Doğrulamalar
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Diagnostic", result.get(0).getDiagnostic());

        // Repository ve ModelMapper metodlarının çağrıldığını doğrula
        verify(reportRepository).findAll();
        verify(modelMapper).map(report, GetAllReportsResponse.class);
    }

}
