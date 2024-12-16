package com.example.BankSampah.Model.Member;

//import jakarta.persistence.*; // Untuk JPA anotasi seperti @Entity, @Id, @Column, dll
import lombok.*; // Untuk Lombok anotasi seperti @Data, @NoArgsConstructor, @AllArgsConstructor

//@Entity
//@Table(name = "Kecamatan")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Kecamatan {
    //@Id
    //@Column(name = "idKec")
    private long idKec;

    //@Column(name = "namaKec", nullable = false, length = 20)
    private String namaKec;


    
}
