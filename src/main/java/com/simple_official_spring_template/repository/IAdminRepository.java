package com.simple_official_spring_template.repository;

import com.simple_official_spring_template.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IAdminRepository extends JpaRepository<AdminEntity, String> {
    AdminEntity findOneById(String id);
    AdminEntity findByIdAndPassword(String id, String password);
    AdminEntity findByEmail(String email);

    @Query(nativeQuery =true, value = "DELETE FROM admin as t WHERE t.id IN :ids")
    void deleteByIds(@Param("ids") String[] ids);
}
