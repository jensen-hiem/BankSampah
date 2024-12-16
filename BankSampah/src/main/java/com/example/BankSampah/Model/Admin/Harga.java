package com.example.BankSampah.Model.Admin;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Harga {
    private int idSampah;       // Representasi kolom idSampah
    private Date tanggalUbah;   // Representasi kolom tanggalUbah
    private int hargaSampah;    // Representasi kolom hargaSampah
}

