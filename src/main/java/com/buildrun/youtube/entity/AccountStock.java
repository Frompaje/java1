package com.buildrun.youtube.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "accountStock")
public class AccountStock {

    @EmbeddedId
    private AccountStockId id;

    @ManyToOne
    @JoinColumn(name = "accountId")
    @MapsId("accountId")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "stockId")
    @MapsId("stockId")
    private Stock stock;

    @Column(name = "quantity")
    private Integer quantity;


}
