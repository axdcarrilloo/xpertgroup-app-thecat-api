package com.thecat.api.utils;

import com.thecat.api.dtos.BreedDto;

import java.util.ArrayList;
import java.util.List;

public class BreedResources {
    private BreedResources(){}

    public static List<BreedDto> getListBreeds() {
        List<BreedDto> breedsDto = new ArrayList<>();
        breedsDto.add(BreedDto.builder().id("aege").name("Aegean").origin("Greece")
                .description("Native to the Greek islands").build())
        ;
        breedsDto.add(BreedDto.builder().id("bslo").name("British Longhair").origin("United Kingdom")
                .description("The British Longhair is a very laid-back relaxed cat").build())
        ;
        return breedsDto;
    }
}
