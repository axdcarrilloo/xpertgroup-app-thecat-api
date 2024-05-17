package com.thecat.api.services;

import com.thecat.api.dtos.BreedDto;

import java.util.List;
import java.util.Map;

public interface BreedService {
    List<BreedDto> getAll();
    Map<String, Object> getById(String id);
    Map<String, Object> getSearch(String id, String name);
}
