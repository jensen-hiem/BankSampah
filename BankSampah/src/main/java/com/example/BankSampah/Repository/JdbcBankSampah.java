 package com.example.BankSampah.Repository;

 import java.sql.ResultSet;
 import java.sql.SQLException;

 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.jdbc.core.JdbcTemplate;
 import org.springframework.stereotype.Repository;


import com.example.BankSampah.Model.JenisSampah;
import com.example.BankSampah.Model.Member;
//import com.example.BankSampah.Model.Transaksi;

@Repository
public class JdbcBankSampah implements BankSampahRepository{
    @Autowired
    private JdbcTemplate jdbc;

    /* @Override
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
    } */

    @Override
    public Iterable<JenisSampah> findAllNamaSampah() {
        return jdbc.query(
            "SELECT Sampah.namasampah, " +
            "       JenisSampah.namajenis, " +
            "       SUK.namasuk, " +
            "       Harga.hargasampah, " +
            "       Harga.tanggalubah " +
            "FROM Sampah " +
            "INNER JOIN JenisSampah ON Sampah.idjenissampah = JenisSampah.idjenissampah " +
            "INNER JOIN SUK ON Sampah.idsuk = SUK.idsuk " +
            "INNER JOIN Harga ON Sampah.idsampah = Harga.idsampah " +
            "ORDER BY Harga.tanggalubah",
            this::mapRowToSampah
        );
    }
    



    private JenisSampah mapRowToSampah(ResultSet resultSet, int rowNum) throws SQLException {
        return new JenisSampah(
            resultSet.getString("namasampah"),
            resultSet.getString("namajenis"), 
            resultSet.getString("namasuk"),    
            resultSet.getInt("hargasampah"),   
            resultSet.getDate("tanggalubah")  
        );
    }    

    @Override
    public Iterable<Member> findAllMember() {
        return jdbc.query(
            "SELECT " +
            "Pengguna.nama AS nama, " +
            "Member.email AS email, " +
            "Member.noHP AS \"nohp\", " +
            "Member.alamat AS alamat, " +
            "Kelurahan.namaKel AS kelurahanMember " +
            "FROM Member " +
            "INNER JOIN Pengguna ON Member.idPengguna = Pengguna.idPengguna " +
            "INNER JOIN Kelurahan ON Member.idKel = Kelurahan.idKel;",
            this::mapRowToMember
        );
    }

    private Member mapRowToMember(ResultSet resultSet, int rowNum) throws SQLException {
        return new Member(
            resultSet.getString("nama"),     // Sama dengan alias di query
            resultSet.getString("email"),    // Sama dengan alias di query
            resultSet.getString("nohp"), // Sama dengan alias di query
            resultSet.getString("alamat"),   // Sama dengan alias di query
            resultSet.getString("kelurahanMember") // Sama dengan alias di query
        );
    }

    /* @Override
    public Iterable<Transaksi> findAllTransaksiMember(){
        return jdbc.query(
            
        );
    } */
    
    


}

