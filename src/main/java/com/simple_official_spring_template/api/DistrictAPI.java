package com.simple_official_spring_template.api;

import com.simple_official_spring_template.dto.DistrictDTO;
import com.simple_official_spring_template.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DistrictAPI {
    @Autowired
    private IDistrictService districtService;

    @GetMapping(value = "/api-district/{id}")
    public DistrictDTO getDistrictById(@PathVariable(value = "id", required = true) Integer id){
        return districtService.findOneById(id);
    }

    @GetMapping(value = "/api-district")
    public List<DistrictDTO> getDistricts(@RequestParam(value = "cityId", required = false) Integer cityId){
        if (cityId != null){
            return districtService.findByCityId(cityId);
        }
        else {
            return districtService.findAll();
        }
    }


    @PostMapping(value = "/api-district")
    public DistrictDTO createDistrict(@RequestBody DistrictDTO districtDTO){
        return districtService.save(districtDTO);
    }

    @PutMapping(value = "/api-district")
    public DistrictDTO updateDistrict(@RequestBody DistrictDTO districtDTO){
        return districtService.update(districtDTO);
    }

    @DeleteMapping(value = "/api-district")
    public void deleteDistricts(@RequestBody DistrictDTO districtDTO){
        districtService.delete(districtDTO);
    }
}
