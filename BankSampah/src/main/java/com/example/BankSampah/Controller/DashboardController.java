package com.example.BankSampah.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/")
    public String showDashboard() {
        return "index"; // Akan mencari file index.html di templates
    }

    @GetMapping("/kelola-sampah")
    public String kelolaSampah() {
        return "kelola-sampah"; // Akan mencari file kelola-sampah.html di templates
    }

    @GetMapping("/kelola-member")
    public String kelolaMember() {
        return "kelola-member";
    }

    @GetMapping("/kelola-transaksi-member")
    public String kelolaTransaksiMember() {
        return "kelola-transaksi-member";
    }

    @GetMapping("/kelola-transaksi-bank")
    public String kelolaTransaksiBank() {
        return "kelola-transaksi-bank";
    }

    @GetMapping("/laporan-sampah")
    public String laporanSampah() {
        return "laporan-sampah";
    }
}

