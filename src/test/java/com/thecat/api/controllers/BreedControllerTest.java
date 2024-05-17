package com.thecat.api.controllers;

import com.thecat.api.dtos.BreedDto;
import com.thecat.api.services.BreedService;
import com.thecat.api.utils.Constants;
import com.thecat.api.utils.MainResponseResources;
import com.thecat.api.utils.Route;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BreedControllerTest {

    @SuppressWarnings("unused")
    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("unused")
    @MockBean
    private BreedService breedSvc;

    @Test
    void getByIdTestSuccessful() throws Exception {
        BreedDto breedDto = BreedDto.builder().id("bomb").name("Bombay").origin("United States")
                .description("The the golden eyes and the shiny black coa of the Bopmbay is absolutely striking.").build();
        Map<String, Object> mapResponse = new HashMap<>();
        mapResponse.put(Constants.MAP_RESPONSE, breedDto);
        when(breedSvc.getById("bomb")).thenReturn(mapResponse);

        mockMvc.perform(get(Route.BASE+Route.BREED+"/breed_id/bomb")
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(result -> Assertions.assertEquals(Constants.MSG_RESPONSE_SUCCESSFUL_QUERY,
                        Objects.requireNonNull(MainResponseResources.convertToMainResponseFromJSON(result.getResponse().getContentAsString())).getMessage())
                )
        ;
    }

    @Test
    void getAllTestSuccessful() throws Exception {
        List<BreedDto> breedsDto = new ArrayList<>();
        breedsDto.add(BreedDto.builder().id("aege").name("Aegean").origin("Greece")
                .description("Native to the Greek islands").build())
        ;
        breedsDto.add(BreedDto.builder().id("bslo").name("British Longhair").origin("United Kingdom")
                .description("The British Longhair is a very laid-back relaxed cat").build())
        ;
        when(breedSvc.getAll()).thenReturn(breedsDto);

        mockMvc.perform(get(Route.BASE+Route.BREED)
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(result -> Assertions.assertEquals(Constants.MSG_RESPONSE_SUCCESSFUL_QUERY,
                        Objects.requireNonNull(MainResponseResources.convertToMainResponseFromJSON(result.getResponse().getContentAsString())).getMessage())
                )
        ;
    }

}
