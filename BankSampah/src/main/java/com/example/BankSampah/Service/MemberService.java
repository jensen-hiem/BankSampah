package com.example.BankSampah.Service;

import com.example.BankSampah.Model.Member;
import com.example.BankSampah.Repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public void addMember(Member member) {
        memberRepository.save(member);
    }

    public void updateNomorHP(int id, String nomorHP) {
        if (!nomorHP.matches("\\d{10,15}")) {
            throw new IllegalArgumentException("Nomor HP tidak valid. Harus 10-15 digit angka.");
        }
        memberRepository.updateNomorHP(id, nomorHP);
    }

    public void deleteMember(int id) {
        memberRepository.deleteById(id);
    }
}

