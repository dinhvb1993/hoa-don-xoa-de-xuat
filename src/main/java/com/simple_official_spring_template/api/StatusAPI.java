package com.simple_official_spring_template.api;

import com.simple_official_spring_template.dto.StatusDTO;
import com.simple_official_spring_template.service.IStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StatusAPI {
    @Autowired
    private IStatusService statusService;

    @GetMapping(value = "/api-status")
    public List<StatusDTO> getStatuses() {
        return statusService.findAll();
    }

    @GetMapping(value = "/api-status/{id}")
    public StatusDTO getStatusById(@PathVariable(name = "id", required = true) String id) {
        return statusService.findOneById(id);
    }

    @GetMapping(value = "/api-status-get-by-name/{name}")
    public StatusDTO getStatusByName(@PathVariable(name = "name", required = true) String name) {
        return statusService.findOneByName(name);
    }

    @PostMapping(value = "/api-status")
    public StatusDTO createStatus(@RequestBody StatusDTO statusDTO) {
        return statusService.save(statusDTO);
    }

    @PutMapping(value = "/api-status")
    public StatusDTO updateStatus(@RequestBody StatusDTO statusDTO) {
        return statusService.update(statusDTO);
    }

    @DeleteMapping(value = "/api-status")
    public void deleteStatus(@RequestBody StatusDTO statusDTO) {
        statusService.delete(statusDTO);
    }
}
