package com.thecat.api.controllers;

import com.thecat.api.dtos.MainResponseDto;
import com.thecat.api.services.BreedService;
import com.thecat.api.utils.Constants;
import com.thecat.api.utils.Route;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(value = Route.BASE+Route.BREED, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class BreedController {

    private final BreedService breedSvc;

    @GetMapping(value = Route.BREED_SEARCH)
    public ResponseEntity<MainResponseDto> getSearch(@RequestParam(name = "id", required = false) String id,
                                                     @RequestParam(name = "name", required = false) String name) {
        Map<String, Object> map = breedSvc.getSearch(id, name);
        String notFound = (String)map.get(Constants.MAP_NOTFOUND);
        if(Objects.nonNull(notFound)) {
            return new ResponseEntity<>(MainResponseDto.builder().message(Constants.MSG_RESPONSE_FAILED_QUERY)
                    .response(notFound)
                    .build(), HttpStatus.NOT_FOUND)
            ;
        } else {
            return new ResponseEntity<>(MainResponseDto.builder().message(Constants.MSG_RESPONSE_SUCCESSFUL_QUERY)
                    .response(map.get(Constants.MAP_RESPONSE))
                    .build(), HttpStatus.OK)
            ;
        }
    }

    @GetMapping(value = Route.BREED_ID)
    public ResponseEntity<MainResponseDto> getById(@PathVariable String id) {
        Map<String, Object> map = breedSvc.getById(id);
        String notFound = (String)map.get(Constants.MAP_NOTFOUND);
        if(Objects.nonNull(notFound)) {
            return new ResponseEntity<>(MainResponseDto.builder().message(Constants.MSG_RESPONSE_FAILED_QUERY)
                    .response(notFound)
                    .build(), HttpStatus.NOT_FOUND)
            ;
        } else {
            return new ResponseEntity<>(MainResponseDto.builder().message(Constants.MSG_RESPONSE_SUCCESSFUL_QUERY)
                    .response(map.get(Constants.MAP_RESPONSE))
                    .build(), HttpStatus.OK)
            ;
        }
    }

    @GetMapping()
    public ResponseEntity<MainResponseDto> getAll() {
        return new ResponseEntity<>(MainResponseDto.builder().message(Constants.MSG_RESPONSE_SUCCESSFUL_QUERY)
                .response(breedSvc.getAll())
                .build(), HttpStatus.OK)
        ;
    }

}
