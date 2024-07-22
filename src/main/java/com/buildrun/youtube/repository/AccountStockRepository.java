package com.buildrun.youtube.repository;

import com.buildrun.youtube.entity.AccountStock;
import com.buildrun.youtube.entity.AccountStockId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountStockRepository extends JpaRepository<AccountStock, AccountStockId> {
}
