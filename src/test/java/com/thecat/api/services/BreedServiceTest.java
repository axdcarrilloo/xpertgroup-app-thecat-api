package com.thecat.api.services;

import com.thecat.api.dtos.BreedDto;
import com.thecat.api.openfeign.BreedFeign;
import com.thecat.api.services.impl.BreedServiceImpl;
import com.thecat.api.utils.BreedResources;
import com.thecat.api.utils.Constants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class BreedServiceTest {

    @Mock
    private BreedFeign breedFeign;

    @InjectMocks
    private BreedServiceImpl breedSvc;

    @Test
    void getSearchTestSuccessful() {
        when(breedFeign.getAll()).thenReturn(new ResponseEntity<>(BreedResources.getListBreeds(), HttpStatus.OK));

        Map<String, Object> map = breedSvc.getSearch("bslo", "British Longhair");

        assertNotNull(map.get(Constants.MAP_RESPONSE));
        assertEquals("British Longhair", ((BreedDto)map.get(Constants.MAP_RESPONSE)).getName());
        assertEquals("United Kingdom", ((BreedDto)map.get(Constants.MAP_RESPONSE)).getOrigin());
    }

    @Test
    void getByIdTestSuccessful() {
        when(breedFeign.getAll()).thenReturn(new ResponseEntity<>(BreedResources.getListBreeds(), HttpStatus.OK));

        Map<String, Object> map = breedSvc.getById("bslo");

        assertNotNull(map.get(Constants.MAP_RESPONSE));
        assertEquals("British Longhair", ((BreedDto)map.get(Constants.MAP_RESPONSE)).getName());
    }

    @Test
    void getAllTestSuccessful() {
        when(breedFeign.getAll()).thenReturn(new ResponseEntity<>(BreedResources.getListBreeds(), HttpStatus.OK));

        List<BreedDto> breedsResponse = breedSvc.getAll();

        assertFalse(breedsResponse.isEmpty());
        assertEquals(2, breedsResponse.size());
    }
}
