package com.example.BankSampah.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class Member {
    private int idpengguna; 
    private String nohp;
    private String alamat;
    private String nama;
    private String email;
    private int idKel;
}
