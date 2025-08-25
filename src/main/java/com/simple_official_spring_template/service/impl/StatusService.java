package com.simple_official_spring_template.service.impl;

import com.simple_official_spring_template.converter.StatusConverter;
import com.simple_official_spring_template.dto.StatusDTO;
import com.simple_official_spring_template.entity.StatusEntity;
import com.simple_official_spring_template.repository.IStatusRepository;
import com.simple_official_spring_template.service.IStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatusService implements IStatusService {
    @Autowired
    IStatusRepository statusRepository;

    @Autowired
    StatusConverter statusConverter;

    @Override
    public List<StatusDTO> findAll() {
        List<StatusDTO> statusDTOS = new ArrayList<>();

        List<StatusEntity> statusEntities = statusRepository.findAll();
        for (StatusEntity statusEntity: statusEntities) {
            statusDTOS.add(statusConverter.toDTO(statusEntity));
        }

        return statusDTOS;
    }

    @Override
    public StatusDTO findOneById(String id) {
        StatusEntity statusEntity = statusRepository.findOneById(id);
        return statusConverter.toDTO(statusEntity);
    }

    @Override
    public StatusDTO findOneByName(String name) {
        StatusEntity statusEntity = statusRepository.findOneByName(name);
        return statusConverter.toDTO(statusEntity);
    }

    @Override
    public StatusDTO save(StatusDTO statusDTO) {
        StatusEntity statusEntity = statusConverter.toEntity(statusDTO, new StatusEntity());
        statusEntity = statusRepository.save(statusEntity);
        return statusConverter.toDTO(statusEntity);
    }

    @Override
    public StatusDTO update(StatusDTO statusDTO) {
        StatusEntity statusEntity = statusRepository.findOneById(statusDTO.getId());
        statusEntity = statusConverter.toEntity(statusDTO, statusEntity);
        statusEntity = statusRepository.save(statusEntity);
        return statusConverter.toDTO(statusEntity);
    }

    @Override
    public void delete(StatusDTO statusDTO) {
        statusRepository.deleteByIds(statusDTO.getIds());
    }
}