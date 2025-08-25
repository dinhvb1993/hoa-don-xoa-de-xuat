package com.simple_official_spring_template.repository;

import com.simple_official_spring_template.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICommentRepository extends JpaRepository<CommentEntity, String> {
    CommentEntity findOneById(Integer id);
//    List<CommentEntity> findByParentComment(CommentEntity commentEntity);
    List<CommentEntity> findByProductId(Integer productId);

    @Query(nativeQuery =true, value = "DELETE FROM comment as t WHERE t.id IN :ids")
    void deleteByIds(@Param("ids") Integer[] ids);
}
