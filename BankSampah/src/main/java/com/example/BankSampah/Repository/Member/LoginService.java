package com.example.BankSampah.Repository.Member;

import com.example.BankSampah.Model.Member.Pengguna;
import com.example.BankSampah.Repository.Member.loginRepository;

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

        if(user.getRoles().equals("Member")){
            return "Welcome Member: " + user.getNama();
        } else if (user.getRoles().equals("IbuBS")){
            return "Welcome Admin: " + user.getNama();
        }

    
        return "User role unknown!";
    }
}
