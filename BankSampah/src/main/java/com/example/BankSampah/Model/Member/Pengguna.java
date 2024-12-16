package com.example.BankSampah.Model.Member;

import lombok.AllArgsConstructor;
//import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
//@Table(name = "Pengguna")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pengguna {

    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate primary key menggunakan auto-increment
    //@Column(name = "idPengguna")
    private int idPengguna;

    //@Column(name = "username", length = 30, nullable = false)
    private String username;

    //@Column(name = "nama", length = 30)
    private String nama;

    private String password;
}
