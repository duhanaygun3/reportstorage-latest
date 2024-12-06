package com.example.Laborant.business.abstracts;

import java.util.List;

import com.example.Laborant.business.requests.CreateReportRequest;
import com.example.Laborant.business.requests.UpdateReportRequest;
import com.example.Laborant.business.responses.GetAllReportsResponse;
import com.example.Laborant.business.responses.GetReportByIdResponse;

public interface ReportService {

    void add(CreateReportRequest createReportRequest);

    public List<GetAllReportsResponse> getAllReports();

    void update(UpdateReportRequest updateReportRequest);

    GetReportByIdResponse getReportById(int id);

    public List<GetAllReportsResponse> searchByPatient(String firstName, String lastName, String patientNo);// reports
                                                                                                            // üzerinde
                                                                                                            // patient
    // nesnesinin alnları ile
    // arama yapmaya
    // çalışıyoruz

    public List<GetAllReportsResponse> searchByLaborant(String firstName, String lastName);

    public List<GetAllReportsResponse> getAllReportsSorted(String direction);

    public List<GetAllReportsResponse> getAllReportsSorted(boolean isAscending);
}
