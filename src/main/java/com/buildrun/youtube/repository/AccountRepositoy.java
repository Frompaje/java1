package com.buildrun.youtube.repository;

import com.buildrun.youtube.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface AccountRepositoy extends JpaRepository<Account, UUID> {
}
