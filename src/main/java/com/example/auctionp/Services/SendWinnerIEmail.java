package com.example.auctionp.Services;


import com.example.auctionp.Models.Bid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendWinnerIEmail implements IEmailSend {
    @Autowired
    JavaMailSender jms;

    @Override
    public void sendEmail(Object obj) {
        Bid b = (Bid) obj;
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("forskolandawa@gmail.com");
        msg.setTo(b.getUser().getEmail());
        msg.setSubject("You are the highest bidder and have oficcially won the auction,  " + b.getAuction().getAuctionTitle());
        msg.setText(
                "Congratz! \n" +
                        "Acution ID: " + b.getAuction().getAuctionId() + "\n" +
                        "Item won: " + b.getAuction().getAuctionTitle() + "\n" +
                        "Highest bid and therefore the winner:  " + b.getBid() + "\n" +
                        "Congratulations,  " + b.getUser().getUsername() + "!"
        );
        jms.send(msg);
    }
}
