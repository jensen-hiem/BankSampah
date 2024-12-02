package com.example.BankSampah.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BankSampahController {
    // @Autowired
    // private BankSampahRepository repo;
    @GetMapping("/")
    public String UIAdmin(){
        return "index";
    }


    // @PostMapping("/add")
    // public String inputNamaSampah(@RequestParam(required = false) String namaSampah, @RequestParam String jenisSampah, @RequestParam String SUK, @RequestParam float hargaBeli, @RequestParam String tanggal){
    //     this.repo.inputNamaSampah(namaSampah, jenisSampah, SUK, hargaBeli, tanggal);
    //     return "redirect:/";
    // }
}
