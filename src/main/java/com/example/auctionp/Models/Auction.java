package com.example.auctionp.Models;


import com.example.auctionp.Utilities.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Auction {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long auctionId;

    @Column(length = 128)
    private String auctionTitle;
    @Column(length = 1024)
    private String auctionDesc;
    @Column
    private LocalDateTime auctionPosted;
    @Column
    private LocalDateTime auctionEnd;

    @Column(columnDefinition = "integer default 1")
    private Integer active = 1;


    @OneToMany(mappedBy = "auction", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("bid DESC")
    private List<Bid> biddens = new ArrayList<>();


    @Column
    private double auctionPrice;
    @Column
    private double auctionHighest;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @Column(nullable = false)
    private Category auctionCategory;

    public void bid(Bid bid) {
        biddens.sort((b1, b2) -> (int) (b1.getBid() + b2.getBid()));
        biddens.add(bid);
    }

/*    @Override
    public String toString() {
        return "Auction{" +
                "auctionId=" + getAuctionId() +
                ", user=" + getUser() +
                ", auctionCategory=" + getAuctionCategory() +
                ", auctionTitle='" + getAuctionTitle() + '\'' +
                ", auctionDesc='" + getAuctionDesc() + '\'' +
                ", auctionPrice=" + getAuctionPrice() +
                ", auctionHighest=" + getAuctionHighest() +
                ", auctionPosted=" + getAuctionPosted() +
                ", auctionEnd=" + getAuctionEnd() +
                ", biddens=" + getBiddens() +
                ", active=" + getActive() +
                '}';
    }*/
}
