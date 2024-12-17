package com.example.BankSampah.Service;

import com.example.BankSampah.Model.Admin.Member;
import com.example.BankSampah.Model.Admin.TransaksiPenyetoran;
import com.example.BankSampah.Repository.Admin.MemberRepository;
import com.example.BankSampah.Repository.Admin.TransaksiPenyetoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransaksiPenyetoranService {
    private final TransaksiPenyetoranRepository transaksiPenyetoranRepository;

    public TransaksiPenyetoranService(TransaksiPenyetoranRepository transaksiPenyetoranRepository) {
        this.transaksiPenyetoranRepository = transaksiPenyetoranRepository;
    }

    public List<TransaksiPenyetoran> getAllTransaksi() {
        return transaksiPenyetoranRepository.findAll();
    }

    public void saveTransaksi(TransaksiPenyetoran transaksi) {
        transaksiPenyetoranRepository.save(transaksi);
    }
}
