package com.example.BankSampah.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HargaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void updateHarga(int idSampah, int hargaSampah) {
        String sql = "INSERT INTO Harga (idSampah, tanggalUbah, hargaSampah) VALUES (?, NOW(), ?) " +
                     "ON CONFLICT (idSampah, tanggalUbah) DO UPDATE SET hargaSampah = ?";
        jdbcTemplate.update(sql, idSampah, hargaSampah, hargaSampah);
    }
}
