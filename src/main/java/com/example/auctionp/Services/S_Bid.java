package com.example.auctionp.Services;


import com.example.auctionp.Models.Bid;
import com.example.auctionp.Repos.BidRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class S_Bid {
    @Autowired
    BidRepo bidRepo;
    @Autowired
    MailFactory emailFact;

    public void addBid(Bid b) {
        bidRepo.save(b);
        emailFact.sendEmail("bidAdded").sendEmail(b);
    }

    public Bid getMaxBid(Long id) {
        return bidRepo.getMaxBidOnAuction(id);
    }
}
