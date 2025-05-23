package com.expensive.api.dto;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDto {
    private long id;
    private String title;
    private String description;
    private int amount;
    private LocalDateTime createdAt;
    private long paidById;
    private long groupId;
    private Set<Long> splits;
}