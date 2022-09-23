package com.example.auctionp.Services;

import com.example.auctionp.Models.Auction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendCreateAuctionIEmail implements IEmailSend {
    @Autowired
    JavaMailSender jms;

    @Override
    public void sendEmail(Object obj) {
        Auction a = (Auction) obj;
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("forskolandawa@gmail.com");
        msg.setTo(a.getUser().getEmail());
        msg.setSubject("Auction Created: " + a.getAuctionTitle());
        msg.setText(
                "-- Auction Details --\n" +
                        "Auction ID: " + a.getAuctionId() + "\n" +
                        "Auction Title: " + a.getAuctionTitle() + "\n" +
                        "Decending: " + a.getAuctionDesc() + "\n" +
                        "Category: " + a.getAuctionCategory() + "\n" +
                        "Start amount in SEK: " + a.getAuctionPrice() + "\n" +
                        "Auction starts following date: " + a.getAuctionPosted() + "\n" + " and will close:" + a.getAuctionEnd() + "\n" +
                        "Thank you for using our services, " + a.getUser().getUsername() + "."
        );
        jms.send(msg);
    }
}
