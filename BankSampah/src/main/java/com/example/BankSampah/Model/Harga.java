package com.example.BankSampah.Model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Harga {
    private int idSampah;
    private LocalDate tanggalUbah;
    private int hargaSampah;
}