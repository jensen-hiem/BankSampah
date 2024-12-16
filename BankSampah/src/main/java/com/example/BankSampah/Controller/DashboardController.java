package com.example.BankSampah.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    // @GetMapping("/")
    // public String showDashboard() {
    //     return "index"; // Akan mencari file index.html di templates
    // }

    @GetMapping("/kelola-sampah")
    public String kelolaSampah() {
        return "Admin/kelola-sampah"; // Akan mencari file kelola-sampah.html di templates
    }

    @GetMapping("/kelola-member")
    public String kelolaMember() {
        return "Admin/kelola-member";
    }

    @GetMapping("/kelola-transaksi-member")
    public String kelolaTransaksiMember() {
        return "Admin/kelola-transaksi-member";
    }

    @GetMapping("/kelola-transaksi-bank")
    public String kelolaTransaksiBank() {
        return "Admin/kelola-transaksi-bank";
    }

    @GetMapping("/laporan-sampah")
    public String laporanSampah() {
        return "Admin/laporan-sampah";
    }
}

