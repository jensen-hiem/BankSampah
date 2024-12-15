package com.example.BankSampah.Repository;
import com.example.BankSampah.Model.*;

public interface  RepoMember {
    
    Iterable<JenisSampah> findAllSampah();
    Iterable<HistoriMember> findAllHistori(String username);
    Iterable<Laporan> findAllLaporan(String username);
    Iterable<Laporan> findAllLaporanYear(String username);
    Iterable<Laporan> findAllLaporanMonth(String username);
    
}
