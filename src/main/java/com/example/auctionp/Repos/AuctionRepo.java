package com.example.auctionp.Repos;

import com.example.auctionp.Models.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionRepo extends JpaRepository<Auction, Long> {
}
