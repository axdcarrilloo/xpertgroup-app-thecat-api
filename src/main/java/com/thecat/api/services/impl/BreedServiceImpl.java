package com.thecat.api.services.impl;

import com.thecat.api.dtos.BreedDto;
import com.thecat.api.dtos.MainResponseDto;
import com.thecat.api.exceptions.serverfallen.ServerFallen;
import com.thecat.api.openfeign.BreedFeign;
import com.thecat.api.services.BreedService;
import com.thecat.api.utils.Constants;
import feign.RetryableException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
@RequiredArgsConstructor
public class BreedServiceImpl implements BreedService {

    private final BreedFeign breedFeign;

    private List<BreedDto> breedsMain = new ArrayList<>();

    private void loadAllRaces() {
        if(breedsMain.isEmpty()) {
            breedsMain = getAll();
        }
    }

    private Map<String, Object> searchInBreedsMain(String id, String name, String prefix) {
        loadAllRaces();
        AtomicReference<BreedDto> breedAtomic = new AtomicReference<>(new BreedDto());
        Map<String, Object> map = new HashMap<>();
        breedsMain.forEach(breedAux -> {
            switch (prefix) {
                case "id":
                    if(breedAux.getId().equals(id)) {
                        breedAtomic.set(breedAux);
                    }
                break;
                case "name":
                    if(breedAux.getName().equals(name)) {
                        breedAtomic.set(breedAux);
                    }
                break;
                default:
                    if(breedAux.getName().equals(name) && breedAux.getId().equals(id)) {
                        breedAtomic.set(breedAux);
                    }
                break;
            }
        });
        if(Objects.isNull(breedAtomic.get()) || Objects.isNull(breedAtomic.get().getId())) {
            map.put(Constants.MAP_NOTFOUND, "Race not found");
        } else {
            map.put(Constants.MAP_RESPONSE, breedAtomic.get());
        }
        return map;
    }

    @Override
    public Map<String, Object> getSearch(String id, String name) {
        if(Objects.isNull(id) && Objects.isNull(name)) {
            Map<String, Object> map = new HashMap<>();
            map.put("FieldsEmpty", "Fields empty");
            return map;
        }
        if(Objects.isNull(name)) {
            return getById(id);
        }
        if(Objects.isNull(id)) {
            return searchInBreedsMain(null, name, "name");
        } else {
            log.info("BreedServiceImpl.class - getById() -> Cosultando por parametro");
            return searchInBreedsMain(id, name, "");
        }
    }

    @Override
    public Map<String, Object> getById(String id) {
        log.info("BreedServiceImpl.class - getById() -> Cosultando por Id");
        return searchInBreedsMain(id, null,  "id");
    }

    @SneakyThrows
    @Override
    public List<BreedDto> getAll() {
        try {
            ResponseEntity<List<BreedDto>> response = breedFeign.getAll();
            if(response.getStatusCode() == HttpStatus.OK) {
                log.info("BreedServiceImpl.class - getAll() -> Cosultando todas las razas");
                this.breedsMain = response.getBody();
                return response.getBody();
            } else {
                return Collections.emptyList();
            }
        } catch(RetryableException retryableException) {
            throw new ServerFallen(MainResponseDto.builder().message(Constants.MSG_RESPONSE_FALLEN_SERVER)
                    .response(retryableException.getMessage())
                    .build())
            ;
        }
    }
}
