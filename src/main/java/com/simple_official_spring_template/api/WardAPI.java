package com.simple_official_spring_template.api;

import com.simple_official_spring_template.dto.WardDTO;
import com.simple_official_spring_template.service.IWardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WardAPI {
    @Autowired
    private IWardService wardService;

    @GetMapping(value = "/api-ward/{id}")
    public WardDTO getWardById(@PathVariable(value = "id", required = true) Integer id){
        return wardService.findOneById(id);
    }

    @GetMapping(value = "/api-ward")
    public List<WardDTO> getWards(@RequestParam(value = "districtId", required = false) Integer districtId){
        if (districtId != null){
            return wardService.findByDistrictId(districtId);
        }
        else {
            return wardService.findAll();
        }
    }


    @PostMapping(value = "/api-ward")
    public WardDTO createWard(@RequestBody WardDTO wardDTO){
        return wardService.save(wardDTO);
    }

    @PutMapping(value = "/api-ward")
    public WardDTO updateWard(@RequestBody WardDTO wardDTO){
        return wardService.update(wardDTO);
    }

    @DeleteMapping(value = "/api-ward")
    public void deleteWards(@RequestBody WardDTO wardDTO){
        wardService.delete(wardDTO);
    }
}
