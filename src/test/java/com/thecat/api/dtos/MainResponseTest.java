package com.thecat.api.dtos;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MainResponseTest {
    @Test
    void validateFields() {
        MainResponseDto response = MainResponseDto.builder().message("Successful").response("Registred").build();

        assertEquals("Successful", response.getMessage());
        assertEquals("Registred", response.getResponse());
    }
}
