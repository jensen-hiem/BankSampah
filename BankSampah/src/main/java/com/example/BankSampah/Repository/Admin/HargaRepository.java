package com.example.BankSampah.Repository.Admin;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.BankSampah.Model.Admin.Harga;

@Repository
public class HargaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<Harga> findFirstByIdSampahOrderByTanggalUbahDesc(int idSampah) {
        String sql = """
                SELECT idSampah, tanggalUbah, hargaSampah
                FROM Harga
                WHERE idSampah = ?
                ORDER BY tanggalUbah DESC
                LIMIT 1
                """;

        return jdbcTemplate.query(sql, ps -> ps.setInt(1, idSampah), rs -> {
            if (rs.next()) {
                return Optional.of(new Harga(
                        rs.getInt("idSampah"),
                        rs.getDate("tanggalUbah"),
                        rs.getInt("hargaSampah")));
            }
            return Optional.empty();
        });
    }

    public void updateHarga(int idSampah, int hargaSampah) {
        String sql = "INSERT INTO Harga (idSampah, tanggalUbah, hargaSampah) VALUES (?, NOW(), ?) " +
                "ON CONFLICT (idSampah, tanggalUbah) DO UPDATE SET hargaSampah = ?";
        jdbcTemplate.update(sql, idSampah, hargaSampah, hargaSampah);
    }
}