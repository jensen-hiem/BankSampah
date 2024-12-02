package com.example.BankSampah.Model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class JenisSampah {
    private String namasampah;
    private String namajenis;
    private String namasuk;
    private int jumlahsampah;
    private int hargatotal;
    private Date tanggal;
}
