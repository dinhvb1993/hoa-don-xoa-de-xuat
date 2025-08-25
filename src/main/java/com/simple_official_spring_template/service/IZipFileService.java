package com.simple_official_spring_template.service;

import com.simple_official_spring_template.dto.CityDTO;

import java.util.List;

public interface IZipFileService {
    String zipMultipleFile(List<String> urls);
}
