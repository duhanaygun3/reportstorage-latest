package com.example.Laborant.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateReportRequest {

    private int id; // Güncellenmesi gereken Report nesnesinin ID'si
    private String diagnostic;
    private String diagnosticDetail;

    private int patientId; // Güncellenmiş hasta ID'si
    private int laborantId; // Güncellenmiş laborant ID'si
}
