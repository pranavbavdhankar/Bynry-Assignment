package com.example.StockFlow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Response {

    private int statusCode;
    private String message;
    private LocalDateTime timeStamp;

}
