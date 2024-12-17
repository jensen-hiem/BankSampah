package com.example.BankSampah.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BankSampah.Model.Admin.Member;
import com.example.BankSampah.Model.Admin.TransaksiBankSampah;
import com.example.BankSampah.Repository.Admin.TransaksiBankSampahRepository;

@Service
public class TransaksiBankSampahService {
    private final TransaksiBankSampahRepository transaksiRepository;

    @Autowired
    public TransaksiBankSampahService(TransaksiBankSampahRepository transaksiRepository) {
        this.transaksiRepository = transaksiRepository;
    }

    public void saveTransaksi(TransaksiBankSampah transaksi) {
        transaksiRepository.saveTransaksi(transaksi);
    }

    // public List<TransaksiBankSampah> getAllTransaksiBankSampah() {
    //     return transaksiRepository.findAll();
    // }
}
