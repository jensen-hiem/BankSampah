package com.example.BankSampah.Repository.Admin;

import com.example.BankSampah.Model.Admin.TransaksiPenyetoran;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransaksiPenyetoranRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Mengambil semua data transaksi penyetoran.
     */
    public List<TransaksiPenyetoran> findAll() {
        String sql = """
                SELECT t.idBSPusat, p.idPengguna, s.idSampah, t.idTransaksi, p.nama AS namaPengguna, t.tanggal,
                s.namaSampah, ts.jumlahSampah, ts.hargaTotal
                FROM Transaksi t
                JOIN Pengguna p ON t.idPengguna = p.idPengguna
                JOIN TransaksiSampah ts ON t.idTransaksi = ts.idTransaksi
                JOIN Sampah s ON ts.idSampah = s.idSampah
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> new TransaksiPenyetoran(
                rs.getInt("idTransaksi"),
                rs.getDate("tanggal"),
                rs.getString("namaPengguna"),
                rs.getString("namaSampah"),
                rs.getInt("jumlahSampah"),
                rs.getInt("hargaTotal"),
                rs.getInt("idPengguna"),
                rs.getInt("idSampah"),
                rs.getInt("idBSPusat")));
    }

    public void save(TransaksiPenyetoran transaksi) {
        // Insert ke tabel Transaksi
        String sqlTransaksi = """
                INSERT INTO Transaksi (tanggal, tipeTransaksi, idPengguna, idBSPusat)
                VALUES (?, ?, ?, ?)
                """;

        jdbcTemplate.update(
                sqlTransaksi,
                transaksi.getTanggal(),
                2,
                transaksi.getIdPengguna(),
                2);

        // Insert ke tabel TransaksiSampah
        String sqlDetail = """
                INSERT INTO TransaksiSampah (idTransaksi, idSampah, jumlahSampah, hargaTotal)
                VALUES ((SELECT MAX(idTransaksi) FROM Transaksi), ?, ?, ?)
                """;

        jdbcTemplate.update(
                sqlDetail,
                transaksi.getIdSampah(),
                transaksi.getJumlahSampah(),
                transaksi.getHargaTotal());
    }
}
