package com.demo.search.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Getter
public class ErrorMessage {
    private int statusCode;
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();
    private String message;
    private String description;
}
