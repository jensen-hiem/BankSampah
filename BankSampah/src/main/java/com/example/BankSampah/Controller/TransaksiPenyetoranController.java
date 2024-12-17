package com.example.BankSampah.Controller;

import com.example.BankSampah.Model.Admin.Harga;
import com.example.BankSampah.Model.Admin.JenisSampah;
import com.example.BankSampah.Model.Admin.Member;
import com.example.BankSampah.Model.Admin.TransaksiPenyetoran;
import com.example.BankSampah.Service.HargaService;
import com.example.BankSampah.Service.JenisSampahService;
import com.example.BankSampah.Service.MemberService;
import com.example.BankSampah.Service.TransaksiPenyetoranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class TransaksiPenyetoranController {
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false); // Untuk validasi ketat
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    private final MemberService memberService;
    @Autowired
    private TransaksiPenyetoranService service;

    @Autowired
    private HargaService hargaService;

    @Autowired
    private JenisSampahService jenisSampahService;

    @Autowired
    public TransaksiPenyetoranController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/kelola-transaksi-member")
    public String getTransaksiPage(Model model) {
        List<TransaksiPenyetoran> listTransaksi = service.getAllTransaksi();
        model.addAttribute("listTransaksi", listTransaksi);

        List<Member> listMember = memberService.getAllMembers();
        model.addAttribute("listMember", listMember);

        List<JenisSampah> listSampah = jenisSampahService.getAllJenisSampah();
        model.addAttribute("listSampah", listSampah);

        return "Admin/kelola-transaksi-member";
    }

    @PostMapping("/transaksi/add")
    public String addTransaksi(@ModelAttribute TransaksiPenyetoran transaksi) {
        // Ambil harga sampah berdasarkan idSampah dari database
        Optional<Harga> hargaSampah = hargaService.getLatestHargaByIdSampah(transaksi.getIdSampah());

        if (hargaSampah.isPresent()) {
            // Hitung harga total
            int harga = hargaSampah.get().getHargaSampah();
            transaksi.setHargaTotal(harga * transaksi.getJumlahSampah());

            // Simpan transaksi
            service.saveTransaksi(transaksi);

            return "redirect:/kelola-transaksi-member";
        } else {
            // Jika harga tidak ditemukan, kembalikan pesan error
            return "redirect:/kelola-transaksi-member?error=Harga tidak ditemukan untuk ID Sampah.";
        }
    }

    // @GetMapping("/getHarga")
    // @ResponseBody
    // public ResponseEntity<?> getHarga(@RequestParam("idSampah") int idSampah) {
    // // Simulasi harga sampah
    // int harga = 5000; // Harga default (bisa diubah menjadi dari database)
    // return ResponseEntity.ok().body("{\"harga\":" + harga + "}");
    // }
}
