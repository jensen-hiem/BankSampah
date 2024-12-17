package com.example.BankSampah.Repository.Admin;

import com.example.BankSampah.Model.Admin.TransaksiBankSampah;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashMap;

import java.util.Map;

@Repository
public class TransaksiBankSampahRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    
    public void saveTransaksi(TransaksiBankSampah transaksi) {
        // Query untuk INSERT ke Transaksi
        String sqlTransaksi = """
            INSERT INTO Transaksi (tanggal, tipeTransaksi, idPengguna, idBSPusat)
            VALUES (:tanggal, :tipeTransaksi, :idPengguna, :idBSPusat)
            RETURNING idTransaksi;
        """;

        Map<String, Object> paramsTransaksi = new HashMap<>();
        paramsTransaksi.put("tanggal", LocalDate.now());
        paramsTransaksi.put("tipeTransaksi", transaksi.getTipeTransaksi());
        paramsTransaksi.put("idPengguna", transaksi.getIdPengguna());
        paramsTransaksi.put("idBSPusat", transaksi.getIdBSPusat());

        // Ambil ID Transaksi yang baru di-generate
        Integer idTransaksi = namedParameterJdbcTemplate.queryForObject(sqlTransaksi, paramsTransaksi, Integer.class);

        // Query untuk INSERT ke TransaksiSampah
        String sqlTransaksiSampah = """
            INSERT INTO TransaksiSampah (idTransaksi, idSampah, jumlahSampah, hargaTotal)
            VALUES (:idTransaksi, :idSampah, :jumlahSampah, :hargaTotal);
        """;

        Map<String, Object> paramsSampah = new HashMap<>();
        paramsSampah.put("idTransaksi", idTransaksi);
        paramsSampah.put("idSampah", transaksi.getIdSampah());
        paramsSampah.put("jumlahSampah", transaksi.getJumlahSampah());
        paramsSampah.put("hargaTotal", transaksi.getHargaTotal());

        namedParameterJdbcTemplate.update(sqlTransaksiSampah, paramsSampah);
    }
}
