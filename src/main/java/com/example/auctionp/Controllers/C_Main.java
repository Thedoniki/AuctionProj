package com.example.auctionp.Controllers;


import com.example.auctionp.Models.Auction;
import com.example.auctionp.Services.S_Auction;
import com.example.auctionp.Utilities.DateAndTime;
import com.example.auctionp.WebSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@Controller
public class C_Main {


    @Autowired
    WebSecurityConfig confSec;
    @Autowired
    S_Auction aService;
    @Autowired
    DateAndTime dat;

    @RequestMapping(value = "/{pageNr}", method = RequestMethod.GET)
    public String home(Model m, @PathVariable(value = "pageNr") int pageNr) {
        int pageSize = 4;
        Page<Auction> p = aService.findPaginated(pageNr, pageSize);
        List<Auction> auctionL = p.getContent();
        m.addAttribute("aPages", auctionL);
        m.addAttribute("currentP", pageNr);
        m.addAttribute("totPages", p.getTotalPages());
        m.addAttribute("user", confSec.getLoggedInUser());
        return "index";
    }
}
