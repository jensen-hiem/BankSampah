package com.example.BankSampah.Model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JenisSampah {
    private int idSampah;       // ID sampah
    private String namaSampah;  // Nama sampah
    private int idJenisSampah; // ID jenis sampah
    private String jenisSampah; // Nama jenis sampah
    private int idSUK;         // ID satuan (SUK)
    private String satuan;      // Nama satuan
    private int hargaBeli;   // Harga beli terbaru
    private Date tanggal;     // Tanggal harga diubah
}
