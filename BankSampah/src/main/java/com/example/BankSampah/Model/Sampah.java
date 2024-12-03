package com.example.BankSampah.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Sampah {
    private int idSampah;
    private String namaSampah;
    private int idJenisSampah;
    private int idSUK;
}