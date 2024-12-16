package com.example.BankSampah.Model.Admin;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransaksiBankSampah {
    private Long id;
    private String namaBank;
    private String tanggalTransaksi;
    private String jenisSampah;
    private int jumlahSampah;
    private double totalHarga;
}