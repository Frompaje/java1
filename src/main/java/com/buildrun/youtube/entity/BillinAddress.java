package com.buildrun.youtube.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "billinAdress")
public class BillinAddress {

    @Id
    @Column(name = "accoutId")
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "accoutId")
    private Account account;

    @Column(name = "street")
    private String street;

    @Column(name = "number")
    private Integer number;

}
