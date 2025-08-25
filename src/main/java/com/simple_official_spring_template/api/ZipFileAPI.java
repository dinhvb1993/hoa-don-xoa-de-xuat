package com.simple_official_spring_template.api;

import com.simple_official_spring_template.dto.CityDTO;
import com.simple_official_spring_template.dto.support_dto.GeneralTypeDTO;
import com.simple_official_spring_template.service.ICityService;
import com.simple_official_spring_template.service.IZipFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ZipFileAPI {
    @Autowired
    private IZipFileService zipFileService;


    @PostMapping(value = "/api-zip-file")
    public String createZipFile(@RequestBody GeneralTypeDTO generalTypeDTO){
        return zipFileService.zipMultipleFile(generalTypeDTO.getStringLst());
    }


}
