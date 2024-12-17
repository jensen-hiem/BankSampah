package com.example.BankSampah.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.BankSampah.Model.Admin.JenisSampah;
import com.example.BankSampah.Model.Admin.Kelurahan;
import com.example.BankSampah.Model.Admin.TransaksiBankSampah;
import com.example.BankSampah.Service.JenisSampahService;
import com.example.BankSampah.Service.KelurahanService;
import com.example.BankSampah.Service.TransaksiBankSampahService;

@Controller
public class TransaksiBankSampahController {
    
    @Autowired
    private JenisSampahService jenisSampahService;

    @Autowired
    private KelurahanService kelurahanService;

    @Autowired 
    private TransaksiBankSampahService transaksiBankSampahService;


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping("/kelola-transaksi-bank")
    public String getAllJenisSampah(Model model) {
        List<JenisSampah> listSampah = jenisSampahService.getAllJenisSampah();
        model.addAttribute("listSampah", listSampah);

        List<Kelurahan> listKelurahan = kelurahanService.getAllKelurahan();
        model.addAttribute("listKelurahan", listKelurahan);

        return "Admin/kelola-transaksi-bank";
    }

    @PostMapping("/transaksi/bank")
    public String saveTransaksiBank(
            @RequestParam("kelurahan") String kelurahan,
            @RequestParam("tanggal") String tanggal,
            @RequestParam("idSampah") List<Integer> idSampahList,
            @RequestParam("jumlahSampah") List<Integer> jumlahList,
            Model model) {

        int idBSPusat = kelurahanService.getKelurahanByName(kelurahan).getIdKel();

        for (int i = 0; i < idSampahList.size(); i++) {
            TransaksiBankSampah transaksi = new TransaksiBankSampah();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                transaksi.setTanggal(sdf.parse(tanggal));
            } catch (ParseException e) {
                e.printStackTrace();
                // Tambahkan log atau penanganan error jika diperlukan
            }
            transaksi.setTipeTransaksi(1); // misalnya tipe 1 untuk "Transaksi ke Bank Sampah"
            transaksi.setIdBSPusat(idBSPusat);
            transaksi.setIdPengguna(1); // id pengguna, sementara hardcoded
            transaksi.setIdSampah(idSampahList.get(i));
            transaksi.setJumlahSampah(jumlahList.get(i));
            transaksi.setHargaTotal(jenisSampahService.getSampahById(idSampahList.get(i)).getHargaBeli() * jumlahList.get(i));

            transaksiBankSampahService.saveTransaksi(transaksi);
        }

        model.addAttribute("message", "Transaksi berhasil disimpan!");
        return "redirect:/kelola-transaksi-bank";
    }
}
