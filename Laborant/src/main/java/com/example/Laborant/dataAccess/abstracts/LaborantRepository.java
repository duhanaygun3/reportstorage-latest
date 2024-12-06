package com.example.Laborant.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Laborant.entities.concreates.Laborant;

@Repository // Best Practice
public interface LaborantRepository extends JpaRepository<Laborant, Integer> {

}
