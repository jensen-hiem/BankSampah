package com.example.BankSampah.Service;

import com.example.BankSampah.Model.JenisSampah;
import com.example.BankSampah.Repository.HargaRepository;
import com.example.BankSampah.Repository.JenisSampahRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JenisSampahService {
    private final JenisSampahRepository jenisSampahRepository;
    private final HargaRepository hargaRepository;

    @Autowired
    public JenisSampahService(JenisSampahRepository jenisSampahRepository, HargaRepository hargaRepository) {
        this.jenisSampahRepository = jenisSampahRepository;
        this.hargaRepository = hargaRepository;
    }

    public List<JenisSampah> getAllJenisSampah() {
        return jenisSampahRepository.findAll();
    }

    public JenisSampah getSampahById(int id) {
        return jenisSampahRepository.findById(id).orElse(null);
    }

    public void saveSampah(JenisSampah jenisSampah) {
        jenisSampahRepository.updateHarga(jenisSampah.getIdSampah(), jenisSampah.getHargaBeli());
    }

    public void addJenisSampah(JenisSampah jenisSampah) {
        jenisSampahRepository.save(jenisSampah);
    }

    public void deleteJenisSampah(int id) {
        jenisSampahRepository.deleteById(id);
    }

    public void updateHarga(int idSampah, int hargaSampah) {
        // Delegasikan pembaruan harga ke HargaRepository
        hargaRepository.updateHarga(idSampah, hargaSampah);
    }
}
