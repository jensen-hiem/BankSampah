package com.example.BankSampah.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.BankSampah.Model.*;

@Repository
public class jdbcRepoMember implements RepoMember {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Iterable<JenisSampah> findAllSampah() {
        return jdbcTemplate.query(
            "SELECT " +
            "    Sampah.namaSampah, " +
            "    Harga.hargaSampah " +
            "FROM " +
            "    Sampah " +
            "INNER JOIN ( " +
            "    SELECT " +
            "        idSampah, " +
            "        MAX(tanggalUbah) AS updateTerbaru " +
            "    FROM " +
            "        Harga " +
            "    GROUP BY " +
            "        idSampah " +
            ") AS Latest ON Sampah.idSampah = Latest.idSampah " +
            "INNER JOIN Harga ON Harga.idSampah = Sampah.idSampah AND Harga.tanggalUbah = Latest.updateTerbaru;",this::mapRowToDaftar);
    }

    private JenisSampah mapRowToDaftar(ResultSet resultSet, int rowNum) throws SQLException {
        JenisSampah jenisSampah = new JenisSampah();
        jenisSampah.setNamasampah(resultSet.getString("namasampah"));
        jenisSampah.setHargasampah(resultSet.getInt("hargasampah"));
        return jenisSampah;
    }

    @Override
    public Iterable<HistoriMember> findAllHistori(String username){
        String sql = "SELECT " +
            "Sampah.namaSampah, " +
            "JenisSampah.namajenis, " +
            "SUK.namaSuk, " +
            "TransaksiSampah.jumlahSampah, " +
            "TransaksiSampah.hargaTotal, " +
            "Transaksi.tanggal " +
        "FROM " +
            "Member " +
        "INNER JOIN Transaksi ON Member.idPengguna = Transaksi.idPengguna " +
        "INNER JOIN TransaksiSampah ON Transaksi.idTransaksi = TransaksiSampah.idTransaksi " +
        "INNER JOIN Sampah ON TransaksiSampah.idSampah = Sampah.idSampah " +
        "INNER JOIN JenisSampah ON Sampah.idjenissampah = JenisSampah.idjenissampah " +
        "INNER JOIN SUK ON Sampah.idsuk =  SUK.idsuk " +
        "WHERE Member.idPengguna = ( " +
            "SELECT idPengguna " +
            "FROM Pengguna " +
            "WHERE username = ?) " +
        "AND tipeTransaksi = 1 " +
        "ORDER BY Transaksi.tanggal";

        return jdbcTemplate.query(sql, this::mapRowToHistori, username);
    }

    private HistoriMember mapRowToHistori(ResultSet rs, int rowNum) throws SQLException {
        HistoriMember histori  = new HistoriMember();
        histori .setNamasampah(rs.getString("namaSampah"));
        histori .setNamajenis(rs.getString("namajenis"));
        histori .setNamasuk(rs.getString("namaSuk"));
        histori .setJumlahSampah(rs.getInt("jumlahSampah"));
        histori .setHargasampah(rs.getInt("hargaTotal"));
        histori .setTanggal(rs.getDate("tanggal"));
        return histori;
    }

    @Override
    public Iterable<Laporan> findAllLaporan(String username) {
        String sql = """
            SELECT
                Transaksi.tanggal,
                SUM(TransaksiSampah.hargaTotal) AS total_pendapatan
            FROM
                Transaksi
            INNER JOIN
                TransaksiSampah ON Transaksi.idTransaksi = TransaksiSampah.idTransaksi
            WHERE
                Transaksi.idPengguna = (
                    SELECT idPengguna
                    FROM Pengguna
                    WHERE username = ?
                )
                AND Transaksi.tanggal BETWEEN '2024-01-01' AND '2024-12-30'
                AND tipeTransaksi = 1
            GROUP BY
                Transaksi.tanggal;
        """;
        return jdbcTemplate.query(sql,this::mapRowToLaporan,username);
    }

    private Laporan mapRowToLaporan(ResultSet rs, int rowNum) throws SQLException{
        
        Laporan laporan= new Laporan();
        laporan.setTanggal(rs.getDate("tanggal"));
        laporan.setTotalPendapatan(rs.getLong("total_pendapatan"));
        return laporan;

    }

    @Override
    public Iterable<Laporan> findAllLaporanYear(String username) {
        String sql = "SELECT " +
                    "    EXTRACT(YEAR FROM Transaksi.tanggal) AS tahun, " +
                    "    SUM(TransaksiSampah.hargaTotal) AS total_pendapatan " +
                    "FROM " +
                    "    Transaksi " +
                    "INNER JOIN TransaksiSampah ON Transaksi.idTransaksi = TransaksiSampah.idTransaksi " +
                    "WHERE " +
                    "    Transaksi.idPengguna = ( " +
                    "        SELECT idPengguna " +
                    "        FROM Pengguna " +
                    "        WHERE username = ? " + // Perbaikan: Hapus tanda kutip
                    "    ) " +
                    "    AND tipeTransaksi = 1 " +
                    "GROUP BY " +
                    "    EXTRACT(YEAR FROM Transaksi.tanggal);";
        return jdbcTemplate.query(sql, this::mapRowToLaporanYear, username);
    }
    private Laporan mapRowToLaporanYear(ResultSet rs, int rowNum) throws SQLException{
        Laporan laporan= new Laporan();
        laporan.setTahunBulan(""+rs.getInt("tahun"));
        laporan.setTotalPendapatan(rs.getLong("total_pendapatan"));
        return laporan;

    }


    @Override
    public Iterable<Laporan> findAllLaporanMonth(String username) {
        String sql = "SELECT " +
               "    EXTRACT(MONTH FROM Transaksi.tanggal) AS bulan, EXTRACT(YEAR FROM Transaksi.tanggal) AS tahun," +
               "    SUM(TransaksiSampah.hargaTotal) AS total_pendapatan " +
               "FROM " +
               "    Transaksi " +
               "INNER JOIN TransaksiSampah ON Transaksi.idTransaksi = TransaksiSampah.idTransaksi " +
               "WHERE " +
               "    Transaksi.idPengguna = ( " +
               "        SELECT idPengguna " +
               "        FROM Pengguna " +
               "        WHERE username = ? " +
               "    ) " +
               "    AND tipeTransaksi = 1 " +
               "GROUP BY " +
               "    EXTRACT(MONTH FROM Transaksi.tanggal), EXTRACT(YEAR FROM Transaksi.tanggal);";
        return jdbcTemplate.query(sql,this::mapRowToLaporanMonth,username);
    }
    private Laporan mapRowToLaporanMonth(ResultSet rs, int rowNum) throws SQLException{
        String[] bulan = {"Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"};

        String currMonth = ""+bulan[rs.getInt("bulan")-1];
        String currYear = ""+rs.getInt("tahun");
        
        Laporan laporan= new Laporan();
        laporan.setTahunBulan(currMonth+" "+currYear);
        laporan.setTotalPendapatan(rs.getLong("total_pendapatan"));
        return laporan;

    }

}
