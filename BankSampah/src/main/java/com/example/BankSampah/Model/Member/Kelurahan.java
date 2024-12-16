package com.example.BankSampah.Model.Member;

//import jakarta.persistence.*;
import lombok.*;

//@Entity
//@Table(name = "Kelurahan")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Kelurahan {

    //@Id
    //@Column(name = "idKel")
    private Long idKel;

    //@Column(name = "namaKel", nullable = false, length = 50)
    private String namaKel;

    //@ManyToOne
    //@JoinColumn(name = "idKec", nullable = false) // Relasi ke Kecamatan
    private Kecamatan kecamatan;
}
