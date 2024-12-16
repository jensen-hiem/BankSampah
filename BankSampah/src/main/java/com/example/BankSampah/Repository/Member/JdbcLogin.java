package com.example.BankSampah.Repository.Member;

import com.example.BankSampah.Model.Member.Pengguna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class JdbcLogin implements loginRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Pengguna> findUserByUsername(String username) {
        String sql = "select * from pengguna where username = ?";
        return jdbcTemplate.query(sql, this::mapRowToUsers, username);
    }

    private Pengguna mapRowToUsers(ResultSet rs, int rowNum) throws SQLException {
        return new Pengguna(
            rs.getInt("idpengguna"),
            rs.getString("username"),
            rs.getString("nama"),
            rs.getString("password")
        );
    }

    @Override
public int findAdminid(int userId) {
    String sql = "SELECT idpengguna FROM pengguna WHERE idpengguna = ? AND idpengguna IN (8, 9)";
    try {
        return jdbcTemplate.queryForObject(sql, new Object[]{userId}, Integer.class);
    } catch (EmptyResultDataAccessException e) {
        return -1; // Tidak ditemukan
    }
}

@Override
public int findMember(int userId) {
    String sql = "SELECT idpengguna FROM pengguna WHERE idpengguna = ? AND idpengguna NOT IN (8, 9)";
    try {
        return jdbcTemplate.queryForObject(sql, new Object[]{userId}, Integer.class);
    } catch (EmptyResultDataAccessException e) {
        return -1; // Tidak ditemukan
    }
}

}
