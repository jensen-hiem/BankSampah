package com.example.BankSampah.Repository.Admin;

import com.example.BankSampah.Model.Admin.TransaksiKeluar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TransaksiKeluarRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<TransaksiKeluar> findAllTransaksiKeluar() {
        String sql = """
                SELECT t.tanggal, k.namaKel, s.namaSampah, ts.hargaTotal
                FROM Transaksi t
                INNER JOIN TransaksiSampah ts ON t.idTransaksi = ts.idTransaksi
                INNER JOIN Sampah s ON ts.idSampah = s.idSampah
                INNER JOIN BSPusat bs ON t.idBSPusat = bs.idBSPusat
                INNER JOIN Kelurahan k ON bs.idKel = k.idKel
                """;

        return jdbcTemplate.query(sql, new TransaksiKeluarRowMapper());
    }

    private static class TransaksiKeluarRowMapper implements RowMapper<TransaksiKeluar> {
        @Override
        public TransaksiKeluar mapRow(ResultSet rs, int rowNum) throws SQLException {
            TransaksiKeluar transaksiKeluar = new TransaksiKeluar();
            transaksiKeluar.setTanggal(rs.getDate("tanggal"));
            transaksiKeluar.setNamaKelurahan(rs.getString("namaKel"));
            transaksiKeluar.setNamaSampah(rs.getString("namaSampah"));
            transaksiKeluar.setHargaTotal(rs.getInt("hargaTotal"));
            return transaksiKeluar;
        }
    }
}
