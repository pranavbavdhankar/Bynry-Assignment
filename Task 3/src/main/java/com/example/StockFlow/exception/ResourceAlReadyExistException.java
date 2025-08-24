package com.example.StockFlow.exception;

public class ResourceAlReadyExistException extends RuntimeException {
    public ResourceAlReadyExistException(String message) {
        super(message);
    }
}
