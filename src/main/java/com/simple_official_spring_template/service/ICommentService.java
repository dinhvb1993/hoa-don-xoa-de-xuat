package com.simple_official_spring_template.service;

import com.simple_official_spring_template.dto.CommentDTO;

import java.util.List;

public interface ICommentService {
    CommentDTO save(CommentDTO commentDTO);
    CommentDTO update(CommentDTO commentDTO);
    CommentDTO findOneById(Integer id);
    List<CommentDTO> findAll();
//    List<CommentDTO> findByParentId(String parentId);
    List<CommentDTO> findByProductId(Integer productId);
    void delete(CommentDTO commentDTO);
}
