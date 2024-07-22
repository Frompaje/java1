package com.buildrun.youtube.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "stocks")
public class Stock {

    @Id
    @Column(name = "stockId")
    private String stockId;

    @Column(name = "description")
    private String description;

}
