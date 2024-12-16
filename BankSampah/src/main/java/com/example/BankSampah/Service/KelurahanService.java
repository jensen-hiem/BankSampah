package com.example.BankSampah.Service;

import com.example.BankSampah.Model.Admin.Kelurahan;
import com.example.BankSampah.Repository.Admin.KelurahanRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KelurahanService {
    private final KelurahanRepository kelurahanRepository;

    public KelurahanService(KelurahanRepository kelurahanRepository) {
        this.kelurahanRepository = kelurahanRepository;
    }

    public List<Kelurahan> getAllKelurahan() {
        return kelurahanRepository.findAll();
    }

    public Kelurahan getKelurahanByName(String namaKelurahan) {
        // Cari kelurahan berdasarkan nama
        return kelurahanRepository.findByNamaKel(namaKelurahan);
    }
}
