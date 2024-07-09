package com.suhoi.mapper;

import com.suhoi.dto.WalletDto;
import com.suhoi.model.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring"
)
public interface WalletMapper {

    WalletDto toDto(Wallet wallet);
    Wallet toEntity(WalletDto walletDto);
}
