package com.simple_official_spring_template.repository;

import com.simple_official_spring_template.entity.UserPermissionEntity;
import com.simple_official_spring_template.entity.embeddedid.UserPermissionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserPermissionRepository extends JpaRepository<UserPermissionEntity, UserPermissionId> {
    UserPermissionEntity findOneByUserPermissionId(UserPermissionId userPermissionId);
    void deleteByUserPermissionIdIn(UserPermissionId[] userPermissionIds);
}
