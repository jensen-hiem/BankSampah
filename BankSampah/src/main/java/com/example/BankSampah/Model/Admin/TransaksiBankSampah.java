package com.example.BankSampah.Model.Admin;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransaksiBankSampah {
    private int idTransaksi;
    private Date tanggal;
    private int tipeTransaksi; 
    private int idPengguna; 
    private int idBSPusat;
    private int idSampah; 
    private int jumlahSampah;
    private int hargaTotal;
}
