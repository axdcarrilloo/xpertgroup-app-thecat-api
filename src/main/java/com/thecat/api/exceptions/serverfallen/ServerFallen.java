package com.thecat.api.exceptions.serverfallen;

import com.thecat.api.dtos.MainResponseDto;
import lombok.Getter;

@Getter
public class ServerFallen extends Exception {
    private final transient MainResponseDto errorResponse;

    public ServerFallen(MainResponseDto errorResponse) {
        super(errorResponse.getMessage());
        this.errorResponse = errorResponse;
    }
}
