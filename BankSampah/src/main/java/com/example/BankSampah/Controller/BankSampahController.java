package com.example.BankSampah.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.BankSampah.Model.Member.HistoriMember;
import com.example.BankSampah.Model.Member.Laporan;
import com.example.BankSampah.Model.Member.JenisSampah;
import com.example.BankSampah.Model.Member.Member;
import com.example.BankSampah.Repository.Member.BankSampahRepository;
import com.example.BankSampah.Repository.Member.LoginService;
import com.example.BankSampah.Repository.Member.RepoMember;

import jakarta.servlet.http.HttpSession;

@Controller
public class BankSampahController {
    
    @Autowired
    private BankSampahRepository repo;

    @Autowired
    private RepoMember repoMember;

    @Autowired
    private LoginService loginService;

    @GetMapping("/")
    public String login(Model moodel){
        return "login";
    }
     // Post mapping untuk melakukan proses login
    @PostMapping("/login")
    public String loginProcess(@RequestParam String username, @RequestParam String password, Model model,HttpSession session) {
        String result = loginService.login(username, password);
        
        if (result.startsWith("Welcome Admin")) {
            // Simpan username ke session
            session.setAttribute("username", username);
            return "redirect:/admin";
        } else if (result.startsWith("Welcome Member")) {
            // Simpan username ke session
            session.setAttribute("username", username);
            return "redirect:/member";
        } else {
            model.addAttribute("error", result); // Menampilkan error jika login gagal
            return "login"; // Kembali ke halaman login
        }
    }
    

    @GetMapping("/admin")
    public String UIAdmin(Model model){
        Iterable<JenisSampah> jenisSampah = this.repo.findAllNamaSampah();
        Iterable<Member> member=this.repo.findAllMember();
        model.addAttribute("results", jenisSampah);
        model.addAttribute("resultsz", member);

        return "Admin/index";
    }

    @GetMapping("/member")
    public String member(Model model,HttpSession session){
        String username = (String) session.getAttribute("username");
        Iterable<JenisSampah> daftarSampah= this.repoMember.findAllSampah();
        model.addAttribute("daftar", daftarSampah);
        model.addAttribute("user", username);
        return "Member/member";
    }
    @GetMapping("/member1")
    public String member1(Model model,HttpSession session){
        String username = (String) session.getAttribute("username");
        Iterable<JenisSampah> daftarSampah= this.repoMember.findAllSampah();
        model.addAttribute("daftar", daftarSampah);
        model.addAttribute("user", username);
        return "Member/member1";
    }
    @GetMapping("/member2")
    public String member2(Model model,HttpSession session){
        String username = (String) session.getAttribute("username");
        Iterable<HistoriMember> histori=this.repoMember.findAllHistori(username);
        model.addAttribute("histori", histori);
        model.addAttribute("user", username);
        return "Member/member2";
    }
    @GetMapping("/member3")
    public String member3(
            @RequestParam(value = "filter", defaultValue = "semua") String filter,
            Model model, HttpSession session) {

        String username = (String) session.getAttribute("username");
        Iterable<Laporan> laporan = null;

        switch (filter) {
            case "tahun":
                laporan = repoMember.findAllLaporanYear(username);
                break;
            case "bulan":
                laporan = repoMember.findAllLaporanMonth(username);
                break;
            case "semua":
            default:
                laporan = repoMember.findAllLaporan(username);
                break;
        }

        model.addAttribute("user", username);
        model.addAttribute("laporan", laporan);
        model.addAttribute("filter", filter); // Tambahkan filter ke model
        return "Member/member3";
}



}
