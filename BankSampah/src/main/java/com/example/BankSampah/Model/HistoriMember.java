package com.example.BankSampah.Model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HistoriMember {
    private String namasampah;
    private String namajenis;
    private String namasuk;
    private int jumlahSampah;
    private int hargasampah;
    private Date tanggal;
}
