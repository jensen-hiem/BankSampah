package com.example.BankSampah.Repository.Admin;

import com.example.BankSampah.Model.Admin.TransaksiMasuk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TransaksiMasukRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<TransaksiMasuk> findAllTransaksiMasuk() {
        String sql = """
                SELECT t.tanggal, p.nama, s.namaSampah, ts.hargaTotal
                FROM Transaksi t
                INNER JOIN TransaksiSampah ts ON t.idTransaksi = ts.idTransaksi
                INNER JOIN Member m ON t.idPengguna = m.idPengguna
                INNER JOIN Pengguna p ON p.idPengguna = m.idPengguna
                INNER JOIN Sampah s ON ts.idSampah = s.idSampah
                """;

        return jdbcTemplate.query(sql, new TransaksiMasukRowMapper());
    }

    private static class TransaksiMasukRowMapper implements RowMapper<TransaksiMasuk> {
        @Override
        public TransaksiMasuk mapRow(ResultSet rs, int rowNum) throws SQLException {
            TransaksiMasuk transaksiMasuk = new TransaksiMasuk();
            transaksiMasuk.setTanggal(rs.getDate("tanggal"));
            transaksiMasuk.setNamaMember(rs.getString("nama"));
            transaksiMasuk.setNamaSampah(rs.getString("namaSampah"));
            transaksiMasuk.setHargaTotal(rs.getInt("hargaTotal"));
            return transaksiMasuk;
        }
    }
}
