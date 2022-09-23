package com.example.auctionp.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailFactory {
    @Autowired
    SendCreateAuctionIEmail sendItemForAuctionConf;
    @Autowired
    SendBidAddedIEmail notifyBidEmail;
    @Autowired
    SendWinnerIEmail wEmail;

    public IEmailSend sendEmail(String k) {

        IEmailSend es;
        switch (k) {
            case "createAuction":
                es = sendItemForAuctionConf;
                break;
            case "bidAdded":
                es = notifyBidEmail;
                break;
            case "winner":
                es = wEmail;
                break;
            default:
                throw new IllegalStateException("Value not approved: " + k);
        }
        return es;
    }
}
