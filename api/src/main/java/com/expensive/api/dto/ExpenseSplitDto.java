package com.expensive.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseSplitDto {
    private long id;
    private long expenseId;
    private int amount;
    private long payeeId;
    private long payerId;
    private boolean isSettled;
}
