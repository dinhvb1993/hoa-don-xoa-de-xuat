package com.simple_official_spring_template.repository;

import com.simple_official_spring_template.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPermissionRepository extends JpaRepository<PermissionEntity, Integer> {
    PermissionEntity findOneById(Integer id);
    PermissionEntity findOneByName(String name);
    void deleteByIdIn(Integer[] ids);
}
