package com.example.BankSampah.Repository.Member;
import com.example.BankSampah.Model.Member.*;

public interface  RepoMember {
    
    Iterable<JenisSampah> findAllSampah();
    Iterable<HistoriMember> findAllHistori(String username);
    Iterable<Laporan> findAllLaporan(String username);
    Iterable<Laporan> findAllLaporanYear(String username);
    Iterable<Laporan> findAllLaporanMonth(String username);
    
}
