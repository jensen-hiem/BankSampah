package com.example.BankSampah.Model.Member;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Laporan {
    private Date tanggal;
    private String tahunBulan;
    private long totalPendapatan;
}
