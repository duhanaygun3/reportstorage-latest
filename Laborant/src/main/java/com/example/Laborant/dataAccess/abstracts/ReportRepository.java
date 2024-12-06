package com.example.Laborant.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Laborant.entities.concreates.Report;

@Repository // Best Practice
public interface ReportRepository extends JpaRepository<Report, Integer> {

        @Query("SELECT r FROM Report r " +
                        "WHERE (:firstName IS NULL OR r.patient.firstName LIKE %:firstName%) " +
                        "AND (:lastName IS NULL OR r.patient.lastName LIKE %:lastName%) " +
                        "AND (:patientNo IS NULL OR r.patient.patientNo LIKE %:patientNo%)")
        List<Report> searchByPatient(
                        @Param("firstName") String firstName,
                        @Param("lastName") String lastName,
                        @Param("patientNo") String patientNo);

        @Query("SELECT r FROM Report r" +
                        " WHERE (:firstName IS NULL OR r.laborant.firstName LIKE %:firstName%)" +
                        "AND (:lastName IS NULL OR r.laborant.lastName LIKE%:lastName%)")
        List<Report> searchByLaborant(
                        @Param("firstName") String firstName,
                        @Param("lastName") String lastName);

}
