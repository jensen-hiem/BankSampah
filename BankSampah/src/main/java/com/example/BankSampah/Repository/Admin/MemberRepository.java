package com.example.BankSampah.Repository.Admin;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.BankSampah.Model.Admin.Member;

import java.util.List;

@Repository
public class MemberRepository {
    private final JdbcTemplate jdbcTemplate;

    public MemberRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Member> findAll() {
        String sql = """
            SELECT p.idPengguna, p.nama, m.email, m.noHp, m.alamat, m.idKel, k.namaKel
            FROM member m
            JOIN pengguna p ON m.idPengguna = p.idPengguna
            JOIN kelurahan k ON m.idKel = k.idKel
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> new Member(
                rs.getInt("idPengguna"),
                rs.getString("nama"),
                rs.getString("email"),
                rs.getString("noHp"),
                rs.getString("alamat"),
                rs.getInt("idKel"),
                rs.getString("namaKel")
        ));
    }

    public int save(Member member) {
        String insertPengguna = "INSERT INTO Pengguna (nama, username, password) VALUES (?, ?, 'password') RETURNING idPengguna";

        Integer idPengguna = jdbcTemplate.queryForObject(insertPengguna, Integer.class, member.getNama(), member.getNama());

        if (idPengguna == null) {
            throw new RuntimeException("Gagal mendapatkan ID pengguna baru");
        }

        String insertMember = """
            INSERT INTO member (idPengguna, noHp, alamat, email, idKel) 
            VALUES (?, ?, ?, ?, ?)
        """;
        return jdbcTemplate.update(insertMember,
                idPengguna,
                member.getNoHp(),
                member.getAlamat(),
                member.getEmail(),
                member.getIdKel());
    }

    public void updateNomorHP(int idPengguna, String nomorHP) {
        String sql = "UPDATE member SET noHP = ? WHERE idPengguna = ?";
        jdbcTemplate.update(sql, nomorHP, idPengguna);
    }

    public void deleteById(int idPengguna) {
        String deleteSql = "DELETE FROM member WHERE idPengguna = ?";
        jdbcTemplate.update(deleteSql, idPengguna);
    }
}

