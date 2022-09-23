package com.example.auctionp.Utilities;


import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class DateAndTime {
    private DateTimeFormatter ft = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    public LocalDateTime auctionTime(String done) {
        String aDone = done + " 00:00";
        LocalDateTime dt = LocalDateTime.parse(aDone, ft);
        return dt;
    }

}
