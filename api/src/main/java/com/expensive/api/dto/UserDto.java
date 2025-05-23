package com.expensive.api.dto;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private long id;
    private String username;
    private String email;
    private String password;
    private boolean isEnabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Set<Long> groupsCreated;
    private Set<Long> groupsJoined;
    private Set<Long> expensesPaid;
    private Set<Long> splitsToPay;
    private Set<Long> splitsToCollect;
    private Set<Long> settlementsToPay;
    private Set<Long> settlementsToCollect;
}
