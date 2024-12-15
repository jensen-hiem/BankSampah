package com.example.BankSampah.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransaksiMember {
    private Long id;
    private String username;
    private String tanggalTransaksi;
    private String jenisSampah;
    private int jumlahSampah;
    private double totalHarga;
}