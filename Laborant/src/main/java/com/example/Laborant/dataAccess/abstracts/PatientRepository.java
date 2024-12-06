package com.example.Laborant.dataAccess.abstracts;

import org.springframework.stereotype.Repository;

import com.example.Laborant.entities.concreates.Patient;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository // Springboot un şuanki versiyonunda bu anotasyona gerek yoktur JpaRepository
            // extend edildiğinde bunun bir repository olduğu uygulama tarafından
            // anlaşılmaktadır. (Best practice)
public interface PatientRepository extends JpaRepository<Patient, Integer> {

}
