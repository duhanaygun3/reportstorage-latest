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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name = "patients") // Veri tabanındad karşılık geldiği tabloyu işaret ediyoruz
@Data // Get Set metodlarını lombok kullanarak ekliyoruz
@AllArgsConstructor // Tüm argumanları içeren const.
@NoArgsConstructor // argümansız conts.
@Entity //
@EqualsAndHashCode
public class Patient {

    @Id // Db de idyi temsil edecek eleman
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id değerinin nasıl arttırılacağını seçilir. Burda otomatik
                                                        // olarak arttırılacaktır.
    @Column(name = "Id") // database de karşılık gelen sütünun ismidir.
    private int id;

    @Column(name = "Name", nullable = false)
    private String firstName;

    @Column(name = "LastName", nullable = false)
    private String lastName;

    @Column(name = "PatientNo", unique = true, nullable = false)
    private String patientNo;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Report> reports;

}
