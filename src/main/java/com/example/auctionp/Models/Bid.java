package com.example.auctionp.Models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor// https://www.baeldung.com/intro-to-project-lombok
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long bidId;
    @Column
    private double bid;

    @Column
    private LocalDateTime bidDate;

    @ManyToOne
    @JoinColumn(name = "auctionId", nullable = false)
    private Auction auction;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    public String getBidDate() {
        DateTimeFormatter formedTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return this.bidDate.format(formedTime);
    }


/*    @Override
    public String toString() {
        return "Bid{" +
                "bidId=" + getBidId() +
                ", bid=" + getBid() +
                ", bidDate=" + getBidDate() +
                ", auction=" + getAuction() +
                ", user=" + getUser() +
                '}';
    }*/
}


