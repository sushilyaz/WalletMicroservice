package com.suhoi.dto;

import com.suhoi.model.OperationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalletDto {
    private UUID valletId;
    private OperationType operationType;
    private BigDecimal amount;
}
