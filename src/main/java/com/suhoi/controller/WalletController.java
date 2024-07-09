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
    public ResponseEntity<WalletDto> updateWallet(@RequestBody WalletDto walletDto) {
        WalletDto res = walletService.updateWallet(walletDto);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/wallets/{id}")
    public ResponseEntity<WalletDto> getWallet(@PathVariable UUID id) {
        WalletDto res = walletService.getWallet(id);
        return ResponseEntity.ok().body(res);
    }
}
