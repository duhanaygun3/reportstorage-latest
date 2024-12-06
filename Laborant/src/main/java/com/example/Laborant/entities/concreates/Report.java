package com.example.Laborant.entities.concreates;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name = "reports")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Diagnostic", nullable = false)
    private String diagnostic;

    @Column(name = "DiagnosticDetail", nullable = false)
    private String diagnosticDetail;

    @Column(name = "ReportDate")
    @Temporal(TemporalType.DATE)
    private Date reportDate;

    @Lob // JPA'nin büyük boyutlu veri türlerini desteklemek için kullanılan bir
         // anotasyondur
    @Basic(fetch = FetchType.LAZY) // Bu, alanın yalnızca ihtiyaç duyulduğunda yüklenmesini sağlar.
    @Column(name = "Photo")
    private byte[] photo; // Fotoğraf alanı

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false) // Patient tablosuna yabancı anahtar
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "laborant_id", nullable = false) // Laborant tablosuna yabancı anahtar
    private Laborant laborant;

}
