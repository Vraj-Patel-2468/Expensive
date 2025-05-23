package com.expensive.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SettlementDto {
    private long id;
    private long payeeId;
    private long payerId;
    private int amount;
}
