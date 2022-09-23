package com.example.auctionp.Repos;


import com.example.auctionp.Models.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BidRepo extends JpaRepository<Bid, Long> {

    @Query("SELECT b FROM Bid b WHERE b.auction.auctionId = ?1 and b.bid=b.auction.auctionHighest")
    public Bid getMaxBidOnAuction(Long id);
}

