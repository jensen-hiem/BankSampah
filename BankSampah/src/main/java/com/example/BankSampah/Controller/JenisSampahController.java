package com.example.BankSampah.Controller;


import com.example.BankSampah.Model.Admin.JenisSampah;
import com.example.BankSampah.Service.JenisSampahService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/sampah")
public class JenisSampahController {

    @Autowired
    private JenisSampahService jenisSampahService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }


    @GetMapping
    public String getAllJenisSampah(Model model) {
        List<JenisSampah> listSampah = jenisSampahService.getAllJenisSampah();
        model.addAttribute("listSampah", listSampah);

        return "Admin/kelola-sampah";
    }

    @PostMapping("/add")
    public String addJenisSampah(@RequestParam("idJenisSampah") int idJenisSampah, @RequestParam("idSUK") int idSUK, @ModelAttribute JenisSampah jenisSampah) {
        jenisSampahService.addJenisSampah(jenisSampah);
        return "redirect:/sampah";
    }

    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity<String> updateHarga(@RequestParam int idSampah, @RequestParam int hargaBeli) {
        try {
            // Delegasikan pembaruan harga ke service
            jenisSampahService.updateHarga(idSampah, hargaBeli);
            return ResponseEntity.ok("Harga berhasil diperbarui atau ditambahkan");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Terjadi kesalahan: " + e.getMessage());
        }
    }
}
