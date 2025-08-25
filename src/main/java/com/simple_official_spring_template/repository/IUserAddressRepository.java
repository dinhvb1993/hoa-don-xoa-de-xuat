package com.simple_official_spring_template.repository;

import com.simple_official_spring_template.entity.UserAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUserAddressRepository extends JpaRepository<UserAddressEntity, Integer> {
    List<UserAddressEntity> findByUserId(String customerId);
    UserAddressEntity findOneById(Integer id);

    @Query(nativeQuery =true, value = "DELETE FROM user_address as t WHERE t.id IN :ids")
    void deleteByIds(@Param("ids") Integer[] ids);
}
