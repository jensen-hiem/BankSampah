package com.example.BankSampah.Repository;

import com.example.BankSampah.Model.Pengguna;
import com.example.BankSampah.Repository.loginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

    @Autowired
    private loginRepository loginRepository;

    public String login(String username, String password) {
        List<Pengguna> userList = loginRepository.findUserByUsername(username);
    
        if (userList.isEmpty()) {
            return "User not found!";
        }
    
        Pengguna user = userList.get(0);
    
        if (!user.getPassword().equals(password)) {
            return "Wrong password!";
        }
    
        int adminId = loginRepository.findAdminid(user.getIdPengguna());
        if (adminId != -1) {
            return "Welcome Admin: " + user.getNama();
        }
    
        int memberId = loginRepository.findMember(user.getIdPengguna());
        if (memberId != -1) {
            return "Welcome Member: " + user.getNama();
        }
    
        return "User role unknown!";
    }
}
