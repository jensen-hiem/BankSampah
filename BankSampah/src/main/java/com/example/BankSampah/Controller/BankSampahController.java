// package com.example.BankSampah.Controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestParam;

// import com.example.BankSampah.Model.JenisSampah;
// import com.example.BankSampah.Model.Member;
// import com.example.BankSampah.Repository.BankSampahRepository;

// @Controller
// public class BankSampahController {
// @Autowired
// private BankSampahRepository repo;

// @GetMapping("/")
// public String UIAdmin(Model model){
// Iterable<JenisSampah> jenisSampah = this.repo.findAllNamaSampah();
// Iterable<Member> member=this.repo.findAllMember();
// model.addAttribute("results", jenisSampah);
// model.addAttribute("resultsz", member);

// return "index";
// }
// }
