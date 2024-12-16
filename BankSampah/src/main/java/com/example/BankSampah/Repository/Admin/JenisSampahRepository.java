package com.example.BankSampah.Repository.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.BankSampah.Model.Admin.JenisSampah;

import java.util.List;
import java.util.Optional;

@Repository
public class JenisSampahRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<JenisSampah> findAll() {
        String sql = 
        """
        SELECT s.idSampah, s.namaSampah, j.idJenisSampah, j.namaJenis AS jenisSampah,suk.idSUK, suk.namaSUK AS satuan,
        h.hargaSampah AS hargaBeli, h.tanggalUbah AS tanggal
        FROM Sampah s
        JOIN JenisSampah j ON s.idJenisSampah = j.idJenisSampah
        JOIN SUK suk ON s.idSUK = suk.idSUK
        LEFT JOIN Harga h ON s.idSampah = h.idSampah
        WHERE h.tanggalUbah = (SELECT MAX(tanggalUbah) 
        FROM Harga WHERE idSampah = s.idSampah);
        """;
        return jdbcTemplate.query(sql, (rs, rowNum) -> new JenisSampah(
                rs.getInt("idSampah"),
                rs.getString("namaSampah"),
                rs.getInt("idJenisSampah"),
                rs.getString("jenisSampah"),
                rs.getInt("idSUK"),
                rs.getString("satuan"),
                rs.getInt("hargaBeli"),
                rs.getDate("tanggal")
        ));
    }

    public Optional<JenisSampah> findById(int id) {
        String sql = 
        """
        SELECT s.idSampah, s.namaSampah, j.idJenisSampah, j.namaJenis AS jenisSampah, 
        suk.idSUK, suk.namaSUK AS satuan, h.hargaSampah AS hargaBeli, h.tanggalUbah AS tanggal
        FROM Sampah s
        JOIN JenisSampah j ON s.idJenisSampah = j.idJenisSampah
        JOIN SUK suk ON s.idSUK = suk.idSUK
        LEFT JOIN Harga h ON s.idSampah = h.idSampah
        WHERE s.idSampah = ? AND h.tanggalUbah = (SELECT MAX(tanggalUbah) FROM Harga WHERE idSampah = s.idSampah)
        """;
        
        return jdbcTemplate.query(sql, ps -> ps.setInt(1, id), rs -> {
            if (rs.next()) {
                return Optional.of(new JenisSampah(
                    rs.getInt("idSampah"),
                    rs.getString("namaSampah"),
                    rs.getInt("idJenisSampah"),
                    rs.getString("jenisSampah"),
                    rs.getInt("idSUK"),
                    rs.getString("satuan"),
                    rs.getInt("hargaBeli"),
                    rs.getDate("tanggal")
                ));
            }
            return Optional.empty();
        });
    }
    
    public void updateHarga(int idSampah, int hargaBeli) {
        String sql = "INSERT INTO Harga (idSampah, tanggalUbah, hargaSampah) VALUES (?, NOW(), ?)";
        jdbcTemplate.update(sql, idSampah, hargaBeli);
    }

    public void save(JenisSampah jenisSampah) {
        String sqlSampah = "INSERT INTO Sampah (namaSampah, idJenisSampah, idSUK) VALUES (?, ?, ?)";
        jdbcTemplate.update(sqlSampah, jenisSampah.getNamaSampah(), jenisSampah.getIdJenisSampah(), jenisSampah.getIdSUK());

        String sqlHarga = "INSERT INTO Harga (idSampah, tanggalUbah, hargaSampah) VALUES ((SELECT MAX(idSampah) FROM Sampah), ?, ?)";
        jdbcTemplate.update(sqlHarga, jenisSampah.getTanggal(), jenisSampah.getHargaBeli());
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM Sampah WHERE idSampah = ?";
        jdbcTemplate.update(sql, id);
    }
}
