package com.example.BankSampah.Repository.Member;

import java.util.List;
import com.example.BankSampah.Model.Member.*;

public interface loginRepository {
    List<Pengguna> findUserByUsername(String username);
    int findAdminid(int userId);
    int findMember(int userId);
    
}
