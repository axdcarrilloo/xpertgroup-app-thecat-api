package com.thecat.api.dtos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BreedDtoTest {

    @Test
    void validateFields() {
        BreedDto breedDto = BreedDto.builder().id("bomb").name("Bombay").origin("United States")
                .description("The the golden eyes and the shiny black coa of the Bopmbay is absolutely striking.").build();

        assertEquals("bomb", breedDto.getId());
        assertEquals("Bombay", breedDto.getName());
    }

}
