package com.example.BankSampah.Service;



import com.example.BankSampah.Model.Admin.TransaksiKeluar;
import com.example.BankSampah.Repository.Admin.TransaksiKeluarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransaksiKeluarService {

    @Autowired
    private TransaksiKeluarRepository transaksiKeluarRepository;

    public List<TransaksiKeluar> getAllTransaksiKeluar() {
        return transaksiKeluarRepository.findAllTransaksiKeluar();
    }
}
