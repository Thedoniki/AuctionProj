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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Comparator;

@Controller
public class C_Auction {


    @Autowired
    WebSecurityConfig secConf;
    @Autowired
    DateAndTime dat;
    @Autowired
    S_Auction aServ;
    @Autowired
    S_Bid sServe;

    @RequestMapping(value = "/createAuctionItem", method = RequestMethod.GET)
    public String newAuction(Model m) {
        m.addAttribute("makeAuction", new Auction());
        m.addAttribute("user", secConf.getLoggedInUser());
        return "createAuction";
    }

    @RequestMapping(value = "/auction/create", method = RequestMethod.GET)
    public String createAuction(@ModelAttribute("makeAuction") Auction a, Model m,
                                @RequestParam(value = "category") String cat,
                                @RequestParam(value = "done") String done) {
        aServ.addAuction(a, cat, done);
        return "redirect:/1";
    }

    @RequestMapping(value = "/auction/update/{id}", method = RequestMethod.GET)
    public String updateAItem(@PathVariable(name = "id") Long id,
                              @RequestParam(value = "category") String cat,
                              @RequestParam(value = "done") String done,
                              @RequestParam(value = "auctionTitle") String tit,
                              @RequestParam(value = "auctionPrice") double pr,
                              @RequestParam(value = "auctionDesc") String desc) {
        aServ.updateAuction(id, cat, done, tit, pr, desc);
        return "redirect:/1";
    }

    @RequestMapping("/auction/{id}")
    public ModelAndView auctionView(@PathVariable(name = "id") Long id, Model m) {
        ModelAndView view = new ModelAndView("auctionView");
        Auction a = aServ.getById(id);
        m.addAttribute("allBids", a.getBiddens());
        m.addAttribute("newBid", new Bid());
        m.addAttribute("user", secConf.getLoggedInUser());
        m.addAttribute("auction", a);
        return view;
    }

    //    Edit auction.
    @RequestMapping(value = "/editAuctionItem/{id}", method = RequestMethod.GET)
    public ModelAndView editAuctionItem(@PathVariable(name = "id") Long id, Model m) {
        ModelAndView view = new ModelAndView("updateView");
        Auction a = aServ.getById(id);
        m.addAttribute("editAuction", a);
        view.addObject("aID", a);
        m.addAttribute("user", secConf.getLoggedInUser());
        return view;
    }

    //    Delete auction.
    @RequestMapping("/deleteAuctionItem/{id}")
    public String deleteAuctionItem(@PathVariable(name = "id") Long id) {
        aServ.deleteAuction(id);
        return "redirect:/1";
    }


    // Cancel auction
    @RequestMapping("/cancleAuction/{id}")
    public String cancleAuctionItem(@PathVariable(name = "id") Long id) {
        Auction a = aServ.getById(id);
        Bid w = a.getBiddens().stream().max(Comparator.comparing(Bid::getBid)).get();
        a.setActive(0);
        a.setAuctionTitle(a.getAuctionTitle() + "Winner of this item:  " + w.getUser().getUsername());
        aServ.saveWinner(a, w);
        return "redirect:/1";
    }
}
