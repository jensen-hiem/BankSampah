package com.example.BankSampah.Model.Member;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JenisSampah {
    private String namasampah;
    private String namajenis;
    private String namasuk;
    private int hargasampah;
    private Date tanggalubah;
}
