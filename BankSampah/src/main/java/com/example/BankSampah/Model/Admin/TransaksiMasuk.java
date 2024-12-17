package com.example.BankSampah.Model.Admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransaksiMasuk {
    private Date tanggal;
    private String namaMember;
    private String namaSampah;
    private int hargaTotal;
}
