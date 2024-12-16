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
        String sql = 
        "SELECT * FROM (\n" + //
                        "SELECT Pengguna.idPengguna, username, Pengguna.password, nama, 'Member' AS roles\n" + //
                        "FROM Member \n" + //
                        "JOIN Pengguna ON Pengguna.idPengguna = Member.idPengguna\n" + //
                        "UNION ALL\n" + //
                        "SELECT Pengguna.idPengguna, username, Pengguna.password, nama, 'IbuBS' AS roles\n" + //
                        "FROM Pengguna \n" + //
                        "JOIN IbuBS ON IbuBS.idPengguna = Pengguna.idPengguna\n" + //
                        "ORDER BY idPengguna\n" + //
                        ") WHERE username = ?";
        return jdbcTemplate.query(sql, this::mapRowToUsers, username);
    }

    private Pengguna mapRowToUsers(ResultSet rs, int rowNum) throws SQLException {
        return new Pengguna(
            rs.getInt("idpengguna"),
            rs.getString("username"),
            rs.getString("nama"),
            rs.getString("password"),
            rs.getString("roles")
        );
    }
}
