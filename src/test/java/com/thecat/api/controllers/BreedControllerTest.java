package com.thecat.api.controllers;

import com.thecat.api.dtos.BreedDto;
import com.thecat.api.services.BreedService;
import com.thecat.api.utils.BreedResources;
import com.thecat.api.utils.Constants;
import com.thecat.api.utils.MainResponseResources;
import com.thecat.api.utils.Route;
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
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void getSearchTestSuccessful() throws Exception {
        BreedDto breedDto = BreedDto.builder().id("bomb").name("Bombay").origin("United States")
                .description("The the golden eyes and the shiny black coa of the Bopmbay is absolutely striking.").build();
        Map<String, Object> mapResponse = new HashMap<>();
        mapResponse.put(Constants.MAP_RESPONSE, breedDto);
        when(breedSvc.getSearch("bomb", "Bombay")).thenReturn(mapResponse);

        mockMvc.perform(get(Route.BASE+Route.BREED+Route.BREED_SEARCH+"?id=bomb&name=Bombay")
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(result -> assertEquals(Constants.MSG_RESPONSE_SUCCESSFUL_QUERY,
                        Objects.requireNonNull(MainResponseResources.convertToMainResponseFromJSON(result.getResponse().getContentAsString())).getMessage())
                )
        ;
    }

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
                .andExpect(result -> assertEquals(Constants.MSG_RESPONSE_SUCCESSFUL_QUERY,
                        Objects.requireNonNull(MainResponseResources.convertToMainResponseFromJSON(result.getResponse().getContentAsString())).getMessage())
                )
        ;
    }

    @Test
    void getAllTestSuccessful() throws Exception {
        when(breedSvc.getAll()).thenReturn(BreedResources.getListBreeds());

        mockMvc.perform(get(Route.BASE+Route.BREED)
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(result -> assertEquals(Constants.MSG_RESPONSE_SUCCESSFUL_QUERY,
                        Objects.requireNonNull(MainResponseResources.convertToMainResponseFromJSON(result.getResponse().getContentAsString())).getMessage())
                )
        ;
    }

}
