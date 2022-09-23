package com.example.auctionp.Controllers;


import com.example.auctionp.Models.Auction;
import com.example.auctionp.Models.Bid;
import com.example.auctionp.Services.S_Auction;
import com.example.auctionp.Services.S_Bid;
import com.example.auctionp.Utilities.DateAndTime;
import com.example.auctionp.WebSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDateTime;

@Controller
public class C_Bid {

    @Autowired
    WebSecurityConfig secCof;
    @Autowired
    DateAndTime dat;
    @Autowired
    S_Auction aService;
    @Autowired
    S_Bid bServ;


    @RequestMapping(value = "/auction/bid/{id}", method = RequestMethod.GET)
    public String bid(@PathVariable(value = "id") Long id, @ModelAttribute("bid") Bid b, Model model) {
        Auction a = aService.getById(id);
        if (b.getBid() <= aService.getById(id).getAuctionPrice() || b.getBid() <= aService.getById(id).getAuctionHighest()) {
            return "redirect:/auction" + "/" + a.getAuctionId();
        }
        a.setAuctionHighest(b.getBid());
        b.setUser(secCof.getLoggedInUser());
        b.setBidDate(LocalDateTime.now());
        b.setAuction(aService.getById(id));
        b.getAuction().bid(b);
        bServ.addBid(b);
        return "redirect:/auction" + "/" + a.getAuctionId();
    }
}

