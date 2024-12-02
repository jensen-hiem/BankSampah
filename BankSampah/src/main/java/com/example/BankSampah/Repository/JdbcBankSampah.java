// package com.example.BankSampah.Repository;

// import java.sql.ResultSet;
// import java.sql.SQLException;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.jdbc.core.JdbcTemplate;
// import org.springframework.stereotype.Repository;


import com.example.BankSampah.Model.JenisSampah;

@Repository
public class JdbcBankSampah implements BankSampahRepository{
    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public Iterable<JenisSampah> findAllNamaSampah() {
        return jdbc.query(
            "SELECT Sampah.namaSampah, JenisSampah.namajenis, SUK.namaSuk, TransaksiSampah.jumlahSampah, TransaksiSampah.hargaTotal, Transaksi.tanggal"+
    " FROM Member"+
    " INNER JOIN Transaksi ON Member.idPengguna = Transaksi.idPengguna"+
    " INNER JOIN TransaksiSampah ON Transaksi.idTransaksi = TransaksiSampah.idTransaksi"+
    " INNER JOIN Sampah ON TransaksiSampah.idSampah = Sampah.idSampah"+
    " INNER JOIN JenisSampah ON Sampah.idjenissampah = JenisSampah.idjenissampah"+
    " INNER JOIN SUK ON Sampah.idsuk = SUK.idsuk"+
    " WHERE "+
        " Member.idPengguna = ("+
           " SELECT idPengguna"+
            " FROM Pengguna"+
            " WHERE username = 'Joko')"+
        " AND tipeTransaksi = 1"+
    " ORDER BY "+
        " Transaksi.tanggal",
            this::mapRowToUser);
    }

    private JenisSampah mapRowToUser(ResultSet resultSet, int rowNum) throws SQLException{
        return new JenisSampah(
            resultSet.getString("namasampah"),
            resultSet.getString("namajenis"),
            resultSet.getString("namasuk"),
            resultSet.getInt("jumlahsampah"),
            resultSet.getInt("hargatotal"),
            resultSet.getDate("tanggal")
        );
    }
}

