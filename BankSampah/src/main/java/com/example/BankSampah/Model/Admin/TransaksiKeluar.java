package com.example.BankSampah.Model.Admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransaksiKeluar {
    private Date tanggal;
    private String namaKelurahan;
    private String namaSampah;
    private int hargaTotal;
}
