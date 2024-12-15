package com.example.BankSampah.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@Data
@AllArgsConstructor
@Builder
public class Member {
    private Integer idPengguna;   // ID Pengguna sebagai Primary Key
    private String nama;          // Nama dari tabel Pengguna
    private String email;
    private String noHp;
    private String alamat;
    private Integer idKel;        // ID Kelurahan
    private String kelurahan;     // Nama Kelurahan (untuk tampilan)
}


