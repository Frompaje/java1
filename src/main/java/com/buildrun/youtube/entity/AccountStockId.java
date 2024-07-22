package com.buildrun.youtube.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class AccountStockId {

    @Column(name = "accountId")
    private UUID accountId;

    @Column(name = "stockId")
    private String stockId;

}
