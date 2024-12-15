package com.example.BankSampah.Repository;

import com.example.BankSampah.Model.Kecamatan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class KecamatanRepository {
    private final JdbcTemplate jdbcTemplate;

    public KecamatanRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Kecamatan> findAll() {
        String sql = "SELECT id_kec, nama_kec FROM kecamatan";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Kecamatan(
                rs.getInt("id_kec"),
                rs.getString("nama_kec")
        ));
    }
}
