package com.buildrun.youtube.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "accountId")
    private UUID accountId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "account")
    @PrimaryKeyJoinColumn
    private BillinAddress billinAddress;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "account")
    private List<AccountStock> accountStocks ;

    public Account(UUID accountId, Optional<User> user, Object billinAddress, String description, ArrayList<AccountStock> accountStocks) {
    }
}
