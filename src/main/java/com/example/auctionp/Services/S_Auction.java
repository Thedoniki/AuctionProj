package com.example.auctionp.Services;


import com.example.auctionp.Models.Auction;
import com.example.auctionp.Models.Bid;
import com.example.auctionp.Repos.AuctionRepo;
import com.example.auctionp.Utilities.Category;
import com.example.auctionp.Utilities.DateAndTime;
import com.example.auctionp.WebSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class S_Auction {


    @Autowired
    AuctionRepo repoAut;
    @Autowired
    DateAndTime dat;
    @Autowired
    WebSecurityConfig secConf;
    @Autowired
    com.example.auctionp.Services.MailFactory emailFact;

    public void addAuction(Auction a, String cat, String done) {
        a.setUser(secConf.getLoggedInUser());
        a.setAuctionPosted(LocalDateTime.now());
        a.setAuctionEnd(dat.auctionTime(done));
        provideCategory(a, cat);
        repoAut.save(a);
        emailFact.sendEmail("createAuction").sendEmail(a);
    }

    public void saveWinner(Auction auction, Bid bid) {
        emailFact.sendEmail("winner").sendEmail(bid);
        repoAut.save(auction);
    }

    public void updateAuction(Long id, String cat, String done, String title, double price, String desc) {
        Auction a = repoAut.getOne(id);
        a.setAuctionEnd(dat.auctionTime(done));
        a.setAuctionTitle(title);
        a.setAuctionPrice(price);
        a.setAuctionDesc(desc);
        provideCategory(a, cat);
        repoAut.save(a);
    }

    public Page<Auction> findPaginated(int pageNr, int pageSize) {
        Pageable pageable = PageRequest.of(pageNr - 1, pageSize, Sort.by(Sort.Direction.DESC, "auctionId"));
        return repoAut.findAll(pageable);
    }

    public void provideCategory(Auction a, String cat) {
        switch (cat) {
            case "DRESSES":
                a.setAuctionCategory(Category.DRESSES);
                break;
            case "SKIRTS":
                a.setAuctionCategory(Category.SKIRTS);
                break;
            case "JUMPERS":
                a.setAuctionCategory(Category.JUMPERS);
                break;
            case "BOOTS":
                a.setAuctionCategory(Category.BOOTS);
                break;
            case "HEELS":
                a.setAuctionCategory(Category.HEELS);
                break;
            case "Other":
                a.setAuctionCategory(Category.OTHER);
                break;
        }
    }

    public Auction getById(Long id) {
        return repoAut.findById(id).get();
    }

    public void deleteAuction(Long id) {
        repoAut.deleteById(id);
    }
}

