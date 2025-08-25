package com.simple_official_spring_template.repository;

import com.simple_official_spring_template.entity.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findOneById(String id);
    UserEntity findOneByIdAndPassword(String id, String password);
    UserEntity findOneByEmail(String email);
    UserEntity findOneByFacebookAccount(String facebookId);

    @Query(nativeQuery =true, value = "DELETE FROM user as t WHERE t.id IN :ids")
    void deleteByIds(@Param("ids") String[] ids);


//    List<UserEntity> findAll(Pageable pageable);
}
