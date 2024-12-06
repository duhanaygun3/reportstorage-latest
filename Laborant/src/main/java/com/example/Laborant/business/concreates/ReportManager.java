package com.example.Laborant.business.concreates;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.Laborant.business.abstracts.ReportService;
import com.example.Laborant.business.requests.CreateReportRequest;
import com.example.Laborant.business.requests.UpdateReportRequest;
import com.example.Laborant.business.responses.GetAllReportsResponse;
import com.example.Laborant.business.responses.GetLaborantByIdResponse;
import com.example.Laborant.business.responses.GetPatientByIdResponse;
import com.example.Laborant.business.responses.GetReportByIdResponse;
import com.example.Laborant.core.utilities.mappers.ModelMapperService;
import com.example.Laborant.core.utilities.mappers.ReportMapper;
import com.example.Laborant.dataAccess.abstracts.ReportRepository;
import com.example.Laborant.entities.concreates.Laborant;
import com.example.Laborant.entities.concreates.Patient;
import com.example.Laborant.entities.concreates.Report;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReportManager implements ReportService {

        private ReportRepository reportRepository;
        private ReportFacede reportFacede;
        private ReportMapper reportMapper;

        private ModelMapperService modelMapperService;

        @Override
        public void add(CreateReportRequest createReportRequest) {

                GetLaborantByIdResponse laborant = reportFacede
                                .getLaborantById(createReportRequest.getLaborant_id());// request içinden laborantı
                                                                                       // buluyoruz

                GetPatientByIdResponse patient = reportFacede.getPatientById(createReportRequest.getPatient_id());// request
                                                                                                                  // içinden
                                                                                                                  // patient
                                                                                                                  // buluyoruz.

                Laborant lab = reportMapper.mapToLaborant(laborant);

                Patient patient2 = reportMapper.mapToPatient(patient); // patient buluyoruz

                Report report = modelMapperService.forRequest().map(createReportRequest, Report.class);// reportu map
                                                                                                       // ediyoruz

                report.setLaborant(lab);

                report.setPatient(patient2);

                reportRepository.save(report);

        }

        @Override
        public List<GetAllReportsResponse> getAllReports() {
                List<Report> reports = reportRepository.findAll();

                List<GetAllReportsResponse> repots = reports.stream()
                                .map(report -> modelMapperService.forResponse().map(report,
                                                GetAllReportsResponse.class))
                                .collect(Collectors.toList());
                return repots;

        }

        @Override
        public void update(UpdateReportRequest updateReportRequest) {

                Report report = reportRepository.findById(updateReportRequest.getId()).orElseThrow();

                // ModelMapper'ın ilişkili nesneler (Laborant ve Patient) için yeni nesneler
                // oluşturuyor. Buda kimlik çakısmasına neden olmaktadır o yüzden nesne içerin
                // işleme alanlarını ModelMapperConfig üzerinde skip ediyoruz ve bu alanları (1)
                // ve (2) numaralı işlemlerde manuel olarak atıyoruz
                modelMapperService.forRequest().map(updateReportRequest, report);

                GetLaborantByIdResponse laborantByIdlaborant = reportFacede
                                .getLaborantById(updateReportRequest.getLaborantId());

                Laborant laborant = modelMapperService.forResponse().map(laborantByIdlaborant, Laborant.class);

                GetPatientByIdResponse patientById = reportFacede.getPatientById(updateReportRequest.getPatientId());

                Patient patient = modelMapperService.forResponse().map(patientById, Patient.class);

                // (1)
                report.setLaborant(laborant);
                // (2)
                report.setPatient(patient);

                reportRepository.save(report);

        }

        @Override
        public List<GetAllReportsResponse> searchByPatient(String firstName, String lastName, String patientNo) {

                List<Report> reportsList = reportRepository.searchByPatient(firstName, lastName, patientNo);

                List<GetAllReportsResponse> reports = reportsList.stream()
                                .map(report -> modelMapperService.forResponse().map(report,
                                                GetAllReportsResponse.class))
                                .collect(Collectors.toList());

                return reports;
        }

        @Override
        public List<GetAllReportsResponse> searchByLaborant(String firstName, String lastName) {

                List<Report> reports = reportRepository.searchByLaborant(firstName, lastName);

                List<GetAllReportsResponse> reportsResponses = reports.stream()
                                .map(report -> modelMapperService.forResponse().map(report,
                                                GetAllReportsResponse.class))
                                .collect(Collectors.toList());
                return reportsResponses;
        }

        @Override
        public List<GetAllReportsResponse> getAllReportsSorted(String direction) {

                Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by("reportDate").ascending()
                                : Sort.by("reportDate").descending();

                List<Report> reports = reportRepository.findAll(sort);

                List<GetAllReportsResponse> reportsResponses = reports.stream()
                                .map(report -> modelMapperService.forResponse().map(report,
                                                GetAllReportsResponse.class))
                                .collect(Collectors.toList());

                return reportsResponses;
        }

        // Dinamik sıralama için alternatif metod
        public List<GetAllReportsResponse> getAllReportsSorted(boolean isAscending) {
                Sort sort = isAscending
                                ? Sort.by("reportDate").ascending()
                                : Sort.by("reportDate").descending();

                List<GetAllReportsResponse> getAllReportsResponses = reportRepository.findAll(sort).stream()
                                .map(report -> modelMapperService.forResponse().map(report,
                                                GetAllReportsResponse.class))
                                .collect(Collectors.toList());

                return getAllReportsResponses;
        }

        @Override
        public GetReportByIdResponse getReportById(int id) {

                Report report = reportRepository.findById(id)
                                .orElseThrow(() -> new NoSuchElementException("Report not found with ID: " + id));

                GetReportByIdResponse getReportByIdResponse = modelMapperService.forResponse().map(report,
                                GetReportByIdResponse.class);

                return getReportByIdResponse;
        }

}
