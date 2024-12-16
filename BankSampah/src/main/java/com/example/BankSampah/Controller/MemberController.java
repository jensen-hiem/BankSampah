package com.example.BankSampah.Controller;

import com.example.BankSampah.Model.Admin.Kelurahan;
import com.example.BankSampah.Model.Admin.Member;
import com.example.BankSampah.Service.KelurahanService;
import com.example.BankSampah.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/member")
public class MemberController {
    private final MemberService memberService;
    private final KelurahanService kelurahanService;

    @Autowired
    public MemberController(MemberService memberService, KelurahanService kelurahanService) {
        this.memberService = memberService;
        this.kelurahanService = kelurahanService;
    }

    @GetMapping
    public String getAllMembers(Model model) {
        List<Member> listMember = memberService.getAllMembers();
        model.addAttribute("listMember", listMember);

        List<Kelurahan> listKelurahan = kelurahanService.getAllKelurahan();
        model.addAttribute("listKelurahan", listKelurahan);

        return "Admin/kelola-member";
    }

    @PostMapping("/add")
    public String addMember(@ModelAttribute Member member, @RequestParam String kelurahan) {
        Kelurahan foundKelurahan = kelurahanService.getKelurahanByName(kelurahan);
        if (foundKelurahan != null) {
            member.setIdKel(foundKelurahan.getIdKel());
        } else {
            return "redirect:/member?error=Kelurahan tidak ditemukan";
        }

        memberService.addMember(member);
        return "redirect:/member";
    }


    @PostMapping("/updateNomorHP")
    @ResponseBody
    public String updateNomorHP(@RequestParam int idPengguna, @RequestParam String noHp) {
        try {
            memberService.updateNomorHP(idPengguna, noHp);
            return "Nomor HP berhasil diperbarui";
        } catch (Exception e) {
            return "Gagal memperbarui nomor HP: " + e.getMessage();
        }
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public String deleteMember(@RequestParam int idPengguna) {
        try {
            memberService.deleteMember(idPengguna);
            return "Member berhasil dihapus";
        } catch (Exception e) {
            return "Gagal menghapus member: " + e.getMessage();
        }
    }
}
