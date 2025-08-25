package com.simple_official_spring_template.service.impl;

import com.simple_official_spring_template.converter.ActionConverter;
import com.simple_official_spring_template.dto.ActionDTO;
import com.simple_official_spring_template.entity.ActionEntity;
import com.simple_official_spring_template.repository.IActionRepository;
import com.simple_official_spring_template.service.IActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ActionService implements IActionService {

    @Autowired
    private IActionRepository actionRepository;

    @Autowired
    private ActionConverter actionConverter;

    @Override
    public ActionDTO save(ActionDTO actionDTO) {
        ActionEntity actionEntity = actionConverter.toEntity(actionDTO, new ActionEntity());
        return actionConverter.toDTO(actionRepository.save(actionEntity));
    }

    @Override
    public ActionDTO update(ActionDTO actionDTO) {
        ActionEntity actionEntity = actionRepository.findOneById(actionDTO.getId());
        actionEntity = actionConverter.toEntity(actionDTO, actionEntity);
        return actionConverter.toDTO(actionRepository.save(actionEntity));
    }

    @Override
    public ActionDTO findOneById(Integer id) {
        return actionConverter.toDTO(actionRepository.findOneById(id));
    }

    @Override
    public ActionDTO findOneByName(String name) {
        return actionConverter.toDTO(actionRepository.findOneByName(name));
    }

    @Override
    public List<ActionDTO> findAll() {
        List<ActionDTO> actionDTOS = new ArrayList<>();
        for (ActionEntity actionEntity : actionRepository.findAll()){
            actionDTOS.add(actionConverter.toDTO(actionEntity));
        }
        return actionDTOS;
    }

    @Override
    public void deleteByIdIn(Integer[] ids) {
        actionRepository.deleteByIdIn(ids);
    }

//    @Override
//    public void delete(ActionDTO actionDTO) {
//        actionRepository.deleteAllById(actionDTO.getIds());
//    }
}
