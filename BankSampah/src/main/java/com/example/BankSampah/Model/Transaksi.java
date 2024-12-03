package com.example.BankSampah.Model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Transaksi {
    private int idTransaksi;
    private LocalDate tanggal;
    private int tipeTransaksi;
    private int idPengguna;
    private int idBSPusat;
}
