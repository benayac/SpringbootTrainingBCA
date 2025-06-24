package com.bcatraining.helper.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class ApiResponse<T> {

    private String status = CommonStatus.SUCCESS.name();
    private T data;
    private Instant timestamp = Instant.now();

    public ApiResponse(T data) {
        this.data = data;
    }

    public ApiResponse(CommonStatus status, T data) {
        this.status = status.name();
        this.data = data;
    }
}
