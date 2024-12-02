package com.example.BankSampah.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class Member {
    private int idpengguna;
    private String noHP;
    private String alamat;
    private String email;
    private int idKelurahan;
}
