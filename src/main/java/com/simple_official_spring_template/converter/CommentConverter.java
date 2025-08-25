package com.simple_official_spring_template.converter;

import com.simple_official_spring_template.dto.CommentDTO;
import com.simple_official_spring_template.entity.CommentEntity;
import com.simple_official_spring_template.repository.ICommentRepository;
import com.simple_official_spring_template.repository.IProductRepository;
import com.simple_official_spring_template.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ICommentRepository commentRepository;

    @Autowired
    private IProductRepository productRepository;

    public CommentEntity toEntity(CommentDTO commentDTO, CommentEntity commentEntity){
        if (commentDTO.getId() != null){
            commentEntity.setId(commentDTO.getId());
        }
//        if (commentDTO.getParentId() != null){
//            commentEntity.setParentComment(commentRepository.findOneById(commentDTO.getParentId()));
//        }
        if (commentDTO.getUserId() != null){
            commentEntity.setUser(userRepository.findOneById(commentDTO.getUserId()));
        }
        if (commentDTO.getProductId() != null){
            commentEntity.setProduct(productRepository.findOneById(commentDTO.getProductId()));
        }
        if (commentDTO.getRate() != null){
            commentEntity.setRate(commentDTO.getRate());
        }
        if (commentDTO.getTitle() != null){
            commentEntity.setTitle(commentDTO.getTitle());
        }
        if (commentDTO.getContent() != null){
            commentEntity.setContent(commentDTO.getContent());
        }
        if (commentDTO.getIsPurchased() != null){
            commentEntity.setIsPurchased(commentDTO.getIsPurchased());
        }
        return commentEntity;
    }

    public CommentDTO toDTO(CommentEntity commentEntity){
        return new CommentDTO(
                commentEntity.getId(),
                commentEntity.getUser().getId(),
                commentEntity.getProduct().getId(),
                commentEntity.getRate(),
                commentEntity.getTitle(),
                commentEntity.getContent(),
                commentEntity.getIsPurchased()
        );
    }
}
