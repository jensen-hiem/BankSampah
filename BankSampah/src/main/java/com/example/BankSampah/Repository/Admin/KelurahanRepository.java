package com.example.BankSampah.Repository.Admin;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.BankSampah.Model.Admin.Kelurahan;

import java.util.List;

@Repository
public class KelurahanRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public KelurahanRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Kelurahan> findAll() {
        String sql = "SELECT idkel, namakel, idkec FROM kelurahan";

        RowMapper<Kelurahan> rowMapper = (rs, rowNum) -> new Kelurahan(
            rs.getInt("idkel"),
            rs.getString("namakel"),
            rs.getInt("idkec")
        );

        return namedParameterJdbcTemplate.query(sql, rowMapper);
    }

    public Kelurahan findByNamaKel(String namaKel) {
        String sql = "SELECT idkel, namakel, idkec FROM kelurahan WHERE namakel = :namaKel";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("namaKel", namaKel);

        RowMapper<Kelurahan> rowMapper = (rs, rowNum) -> new Kelurahan(
            rs.getInt("idkel"),
            rs.getString("namakel"),
            rs.getInt("idkec")
        );

        return namedParameterJdbcTemplate.queryForObject(sql, parameters, rowMapper);
    }
}
