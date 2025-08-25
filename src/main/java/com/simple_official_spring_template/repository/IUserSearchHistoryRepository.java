package com.simple_official_spring_template.repository;

import com.simple_official_spring_template.entity.UserSearchHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUserSearchHistoryRepository extends JpaRepository<UserSearchHistoryEntity, Integer> {
    UserSearchHistoryEntity findOneById(Integer id);
    List<UserSearchHistoryEntity> findByUserId(String customerId);

    @Query(nativeQuery =true, value = "DELETE FROM user_search_history as t WHERE t.id IN :ids")
    void deleteByIds(@Param("ids") Integer[] ids);
}
