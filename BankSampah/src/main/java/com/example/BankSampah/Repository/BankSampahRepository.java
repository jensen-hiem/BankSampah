package com.example.BankSampah.Repository;

import com.example.BankSampah.Model.*;

public interface BankSampahRepository {
    //String inputNamaSampah(String namaSampah, String jenisSampah, String SUK, float hargaBeli, String tanggal); //1.
    Iterable<JenisSampah> findAllNamaSampah(); //1.
    // void updateHargaBeliSampah(float hargaBeli, String tanggal); //1.

    Iterable<Member> findAllMember(); //2.

//     String nambahDataMember(); //2.

    Iterable<Transaksi> findAllTransaksiMember(); //3.
//     String inputTransaksiMember(String username, String tanggal, JenisSampah x, int jumlahSampah); //3. Masukin Transaksi
    
//     Iterable<Transaksi> findAllTransaksiBankSampah()//4.
//     String inputTransaksiKePusat(int kelurahan, String tanggalTransaksi, JenisSampah x, int jumlahSampah);//4.Masukin Transaksi Bank Sampah Kelurahan ke Pusat
}