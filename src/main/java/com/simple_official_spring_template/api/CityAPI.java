package com.simple_official_spring_template.api;

import com.simple_official_spring_template.dto.CityDTO;
import com.simple_official_spring_template.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CityAPI {
    @Autowired
    private ICityService cityService;

    @GetMapping(value = "/api-city/{id}")
    public CityDTO getCityById(@PathVariable(value = "id", required = true) Integer id){
        return cityService.findOneById(id);
    }

    @GetMapping(value = "/api-city")
    public List<CityDTO> getCitys(){
        return cityService.findAll();
    }


    @PostMapping(value = "/api-city")
    public CityDTO createCity(@RequestBody CityDTO cityDTO){
        return cityService.save(cityDTO);
    }

    @PutMapping(value = "/api-city")
    public CityDTO updateCity(@RequestBody CityDTO cityDTO){
        return cityService.update(cityDTO);
    }

    @DeleteMapping(value = "/api-city")
    public void deleteCitys(@RequestBody CityDTO cityDTO){
        cityService.delete(cityDTO);
    }
}
