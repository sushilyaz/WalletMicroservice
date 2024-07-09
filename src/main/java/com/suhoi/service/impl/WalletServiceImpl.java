package com.suhoi.service.impl;

import com.suhoi.dto.WalletDto;
import com.suhoi.exception.InsufficientFundsException;
import com.suhoi.exception.WalletNotFoundException;
import com.suhoi.mapper.WalletMapper;
import com.suhoi.model.OperationType;
import com.suhoi.model.Wallet;
import com.suhoi.repository.WalletRepository;
import com.suhoi.service.WalletService;
import jakarta.persistence.OptimisticLockException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    private final WalletMapper walletMapper;

    @Override
    public WalletDto getWallet(UUID walletUid) {
        Wallet walletByValletId = walletRepository.findWalletByValletId(walletUid)
                .orElseThrow(() -> new WalletNotFoundException("Wallet not found exception"));
        return walletMapper.toDto(walletByValletId);
    }

    @Transactional
    @Override
    public WalletDto updateWallet(WalletDto walletDto) {
        while (true) {
            try {

                Wallet wallet = walletRepository.findWalletByValletId(walletDto.getValletId())
                        .orElseThrow(() -> new WalletNotFoundException("Wallet not found exception"));

                if (walletDto.getOperationType() == OperationType.WITHDRAW && wallet.getAmount().compareTo(walletDto.getAmount()) < 0) {
                    throw new InsufficientFundsException("Insufficient funds");
                }

                if (walletDto.getOperationType() == OperationType.DEPOSIT) {
                    wallet.setAmount(wallet.getAmount().add(walletDto.getAmount()));
                } else {
                    wallet.setAmount(wallet.getAmount().subtract(walletDto.getAmount()));
                }
                walletRepository.save(wallet);
                WalletDto dto = walletMapper.toDto(wallet);
                dto.setOperationType(walletDto.getOperationType());
                return dto;
            } catch (OptimisticLockException e) {
                // если произошел конфликт версий, повторяем операцию
            }
        }
    }

}
