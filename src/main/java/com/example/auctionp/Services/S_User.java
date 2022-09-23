package com.example.auctionp.Services;


import com.example.auctionp.Models.User;
import com.example.auctionp.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class S_User {


    @Autowired
    UserRepo uRepo;

    public User findUserByUsername(String username) {
        return uRepo.findByUsername(username);
    }
}
