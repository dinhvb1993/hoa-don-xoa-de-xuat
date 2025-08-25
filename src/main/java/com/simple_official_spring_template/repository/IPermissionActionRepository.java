package com.simple_official_spring_template.repository;

import com.simple_official_spring_template.entity.PermissionActionEntity;
import com.simple_official_spring_template.entity.embeddedid.PermissionActionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPermissionActionRepository extends JpaRepository<PermissionActionEntity, PermissionActionId> {
    PermissionActionEntity findOneByPermissionActionId(PermissionActionId permissionActionId);

    void deleteByPermissionActionIdIn(PermissionActionId[] permissionActionIds);
}
