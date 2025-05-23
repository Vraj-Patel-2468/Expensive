package com.expensive.api.dto;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {
    private long id;
    private String name;
    private LocalDateTime createdAt;
    private Long createdBy;
    private Set<Long> members;
    private Set<Long> expenses;
}