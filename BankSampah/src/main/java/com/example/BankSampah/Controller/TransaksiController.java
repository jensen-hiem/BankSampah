package com.example.BankSampah.Controller;


import com.example.BankSampah.Model.Admin.TransaksiMasuk;
import com.example.BankSampah.Model.Admin.TransaksiKeluar;
import com.example.BankSampah.Service.TransaksiMasukService;
import com.example.BankSampah.Service.TransaksiKeluarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@Controller
public class TransaksiController {

    @Autowired
    private TransaksiMasukService transaksiMasukService;

    @Autowired
    private TransaksiKeluarService transaksiKeluarService;

    // Endpoint untuk Transaksi Masuk
    @GetMapping("/laporan-sampah")
    public String getLaporanTransaksi(Model model) {
        List<TransaksiMasuk> transaksiMasukList = transaksiMasukService.getAllTransaksiMasuk();
        List<TransaksiKeluar> transaksiKeluarList = transaksiKeluarService.getAllTransaksiKeluar();

        model.addAttribute("transaksiMasuk", transaksiMasukList);
        model.addAttribute("transaksiKeluar", transaksiKeluarList);

        return "Admin/laporan-transaksi";
    }
}

