package com.example.BankSampah.Model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Laporan {
    private Date tanggal;
    private int tahunBulan;
    private long totalPendapatan;
}
