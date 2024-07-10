package com.suhoi.controller;

import com.suhoi.dto.WalletDto;
import com.suhoi.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @PostMapping("/wallet")
    public ResponseEntity<Void> updateWallet(@RequestBody WalletDto walletDto) {
        walletService.updateWallet(walletDto);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/wallets/{id}")
    public ResponseEntity<WalletDto> getWallet(@PathVariable UUID id) {
        WalletDto res = walletService.getWallet(id);
        return ResponseEntity.ok().body(res);
    }
}
