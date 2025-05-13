package com.expensive.api.dto;

import lombok.Data;

@Data
public class MessageDto {
    public MessageDto(String string) {
        this.message = string;
    }

    private String message;
}
