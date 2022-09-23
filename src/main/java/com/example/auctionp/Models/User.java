package com.example.auctionp.Models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor// https://www.baeldung.com/intro-to-project-lombok

public class User {

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Auction> auctions = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Bid> bids = new ArrayList<>();
    @Id
    @GeneratedValue
    @Column
    private Integer id;
    private Long userId;
    @Column(nullable = false, unique = true, length = 128)
    private String email;
    @Column(nullable = false, unique = true, length = 64)
    private String username;
    @Column(nullable = false, length = 128)
    private String password;
    @Column(nullable = false)
    private Integer enabled = 1;
    @Column(nullable = false)
    private String role;


/*    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", userId=" + getUserId() +
                ", email='" + getEmail() + '\'' +
                ", username='" + getUsername() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", enabled=" + getEnabled() +
                ", role='" + getRole() + '\'' +
                ", auctions=" + getAuctions() +
                ", bids=" + getBids() +
                '}';
    }*/
}
