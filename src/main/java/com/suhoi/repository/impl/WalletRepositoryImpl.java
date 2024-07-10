package com.suhoi.repository.impl;

import com.suhoi.model.Wallet;
import com.suhoi.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class WalletRepositoryImpl implements WalletRepository {
    private final JdbcTemplate jdbcTemplate;

    public Optional<Wallet> findWalletByValletId(UUID valletId) {
        String sql = "SELECT * FROM wallet_schema.wallets WHERE vallet_id = ?";
        return jdbcTemplate.query(sql, new Object[]{valletId}, rs -> {
            if (rs.next()) {
                return Optional.of(mapRowToWallet(rs));
            } else {
                return Optional.empty();
            }
        });
    }

    public int updateWallet(UUID valletId, BigDecimal amount, long version) {
        String sql = "UPDATE wallet_schema.wallets SET amount = ?, version = version + 1 WHERE vallet_id = ? AND version = ?";
        return jdbcTemplate.update(sql, amount, valletId, version);
    }

    private Wallet mapRowToWallet(ResultSet rs) throws SQLException {
        return Wallet.builder()
                .valletId(UUID.fromString(rs.getString("vallet_id")))
                .amount(rs.getBigDecimal("amount"))
                .createdAt(rs.getTimestamp("created_at"))
                .version(rs.getLong("version"))
                .build();
    }
}
