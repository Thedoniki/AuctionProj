package com.example.auctionp.Services;

import org.springframework.stereotype.Service;

@Service
public interface IEmailSend {
    void sendEmail(Object obj);
}
