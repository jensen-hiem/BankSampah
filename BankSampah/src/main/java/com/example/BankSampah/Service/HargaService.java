package com.example.BankSampah.Service;

import com.example.BankSampah.Model.Admin.Harga;
import com.example.BankSampah.Repository.Admin.HargaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HargaService {

    @Autowired
    private HargaRepository hargaRepository;

    public Optional<Harga> getLatestHargaByIdSampah(int idSampah) {
        return hargaRepository.findFirstByIdSampahOrderByTanggalUbahDesc(idSampah);
    }
}
