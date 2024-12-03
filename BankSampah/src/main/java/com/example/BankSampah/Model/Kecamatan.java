package com.example.BankSampah.Model;

//import jakarta.persistence.*; // Untuk JPA anotasi seperti @Entity, @Id, @Column, dll
import lombok.*; // Untuk Lombok anotasi seperti @Data, @NoArgsConstructor, @AllArgsConstructor


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Kecamatan {
    private long idKec;
    private String namaKec;


    
}
