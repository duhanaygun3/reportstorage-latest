package com.example.Laborant.entities.concreates;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name = "laborants")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode
public class Laborant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "FirstName", nullable = false)
    private String firstName;

    @Column(name = "LastName", nullable = false)
    private String lastName;

    @Column(name = "HospitalIdentityNo", nullable = false, unique = true)
    @Size(min = 7, max = 7, message = "HospitalIdentityNo must be exactly 7 characters.")
    @Pattern(regexp = "\\d{7}", message = "HospitalIdentityNo must contain only digits.")
    private String hospitalIdentityNo;

    @OneToMany(mappedBy = "laborant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Report> reports;

}
