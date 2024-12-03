package com.example.BankSampah.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TransaksiSampah {
    private int idTransaksi;
    private int idSampah;
    private int jumlahSampah;
    private int hargaTotal;
}
