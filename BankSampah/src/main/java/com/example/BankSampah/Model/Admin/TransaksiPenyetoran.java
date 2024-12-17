package com.example.BankSampah.Model.Admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.Transient;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransaksiPenyetoran {
    private int idTransaksi;
    private Date tanggal;
    private String namaPengguna; // Nama dari tabel Pengguna
    private String namaSampah; // Nama dari tabel Sampah
    private int jumlahSampah;
    private int hargaTotal;
    private int idPengguna;
    private int idSampah;
    private int idBSPusat;
}
