package com.example.auctionp.Services;


import com.example.auctionp.Models.Bid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendBidAddedIEmail implements IEmailSend {
    @Autowired
    JavaMailSender jms;

    @Override
    public void sendEmail(Object obj) {
        Bid b = (Bid) obj;
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("forskolandawa@gmail.com");
        msg.setTo(b.getUser().getEmail());
        msg.setSubject("A bid has been placed from your account on following item:  " + b.getAuction().getAuctionTitle());
        msg.setText(
                "Item information bid has been placed on:\n" +

                        "Auction ID: " + b.getAuction().getAuctionId() + "\n" +
                        "Bidding ID: " + b.getBidId() + "\n" +
                        "Auction item name: " + b.getAuction().getAuctionTitle() + "\n" +
                        "Date of bid made: " + b.getBidDate() + "\n" +
                        "Bid Amount in SEK: " + b.getBid() + "\n" +
                        "Auction will close on following date:  " + b.getAuction().getAuctionEnd() + "\n" +
                        "Thank you for using our services, " + b.getUser().getUsername() + "."
        );
        jms.send(msg);
    }
}
