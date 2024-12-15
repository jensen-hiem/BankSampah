package com.example.BankSampah.Service;

import com.example.BankSampah.Model.Kecamatan;
import com.example.BankSampah.Repository.KecamatanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KecamatanService {
    private final KecamatanRepository kecamatanRepository;

    public KecamatanService(KecamatanRepository kecamatanRepository) {
        this.kecamatanRepository = kecamatanRepository;
    }

    public List<Kecamatan> getAllKecamatan() {
        return kecamatanRepository.findAll();
    }
}
