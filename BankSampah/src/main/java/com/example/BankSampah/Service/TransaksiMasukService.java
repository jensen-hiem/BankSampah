package com.example.BankSampah.Service;



import com.example.BankSampah.Model.Admin.TransaksiMasuk;
import com.example.BankSampah.Repository.Admin.TransaksiMasukRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransaksiMasukService {

    @Autowired
    private TransaksiMasukRepository transaksiMasukRepository;

    public List<TransaksiMasuk> getAllTransaksiMasuk() {
        return transaksiMasukRepository.findAllTransaksiMasuk();
    }
}
