package com.suhoi.service;

import com.suhoi.dto.WalletDto;

import java.util.UUID;

public interface WalletService {
    WalletDto getWallet(UUID walletUid);
    WalletDto updateWallet(WalletDto walletDto);
}
