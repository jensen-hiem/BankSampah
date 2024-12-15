package com.example.BankSampah.Repository;

import java.util.List;
import com.example.BankSampah.Model.*;

public interface loginRepository {
    List<Pengguna> findUserByUsername(String username);
    int findAdminid(int userId);
    int findMember(int userId);
    
}
