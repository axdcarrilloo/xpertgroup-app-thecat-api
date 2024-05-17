package com.thecat.api.openfeign;

import com.thecat.api.dtos.BreedDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "BreedFeign", url = "${open-feign.thecat-service.url}")
public interface BreedFeign {
    @GetMapping(value = "/breeds")
    ResponseEntity<List<BreedDto>> getAll();
}
