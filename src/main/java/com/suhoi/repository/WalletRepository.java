package com.suhoi.repository;

import com.suhoi.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public interface WalletRepository {
    Optional<Wallet> findWalletByValletId(UUID valletId);
    int updateWallet(UUID valletId, BigDecimal amount, long version);
//    Optional<Wallet> findWalletByValletId(UUID valletId);
}
