package com.simple_official_spring_template.service.impl;

import com.simple_official_spring_template.converter.CommentConverter;
import com.simple_official_spring_template.dto.CommentDTO;
import com.simple_official_spring_template.entity.CommentEntity;
import com.simple_official_spring_template.entity.ProductEntity;
import com.simple_official_spring_template.entity.ProductRateEntity;
import com.simple_official_spring_template.entity.embeddedid.ProductRateId;
import com.simple_official_spring_template.repository.*;
import com.simple_official_spring_template.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService implements ICommentService {

    @Autowired
    private ICommentRepository commentRepository;

    @Autowired
    private CommentConverter commentConverter;

    @Autowired
    private IItemOfOrderRepository itemOfOrderRepository;

    @Autowired
    private IProductRateRepository productRateRepository;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IUserRepository userRepository;


    @Override
    public CommentDTO save(CommentDTO commentDTO) {

        CommentEntity commentEntity = commentConverter.toEntity(commentDTO, new CommentEntity());

        if (itemOfOrderRepository.findByUserIdAndProductId(commentDTO.getUserId(), commentDTO.getProductId()).size() != 0){
            commentEntity.setIsPurchased(true);
        }


        if (commentEntity.getRate() != null){
            ProductRateEntity productRateEntity = new ProductRateEntity();

            ProductRateEntity productRateEntity1 = productRateRepository.findOneByProductRateId(
                    new ProductRateId(commentDTO.getUserId(), commentDTO.getProductId())
            );
            if (productRateEntity1 != null){
                return null;
            }

            productRateEntity.setProductRateId(new ProductRateId(commentDTO.getUserId(), commentDTO.getProductId()));
            productRateEntity.setRate(commentDTO.getRate());
            productRateEntity.setProduct(productRepository.findOneById(commentDTO.getProductId()));
            productRateEntity.setUser(userRepository.findOneById(commentDTO.getUserId()));



            productRateRepository.save(productRateEntity);

            Integer productRateNumber = null;
            Double averageProductRate = null;
            ProductEntity productEntity = null;

            if (productRateEntity.getProduct() != null){
                productEntity = productRateEntity.getProduct();
                productRateNumber = productEntity.getRateNumber();
                averageProductRate = productEntity.getAverageRate();
                if (productRateNumber == null) {
                    productEntity.setRateNumber(1);
                    productEntity.setAverageRate(productRateEntity.getRate());
                }
                else {
                    productEntity.setRateNumber(productRateNumber + 1);
                    productEntity.setAverageRate((averageProductRate * productRateNumber + productRateEntity.getRate()) / (productRateNumber + 1));
                }
                productRepository.save(productEntity);
            }
        }

        return commentConverter.toDTO(commentRepository.save(commentEntity));
    }

    @Override
    public CommentDTO update(CommentDTO commentDTO) {
        CommentEntity commentEntity = commentRepository.findOneById(commentDTO.getId());
        commentEntity = commentConverter.toEntity(commentDTO, commentEntity);
        return commentConverter.toDTO(commentRepository.save(commentEntity));
    }

    @Override
    public CommentDTO findOneById(Integer id) {
        return commentConverter.toDTO(commentRepository.findOneById(id));
    }

    @Override
    public List<CommentDTO> findAll() {
        List<CommentDTO> commentDTOS = new ArrayList<>();
        for (CommentEntity commentEntity: commentRepository.findAll()){
            commentDTOS.add(commentConverter.toDTO(commentEntity));
        }
        return commentDTOS;
    }

//    @Override
//    public List<CommentDTO> findByParentId(String parentId) {
//        List<CommentDTO> commentDTOS = new ArrayList<>();
//        for (CommentEntity commentEntity: commentRepository.findByParentComment(commentRepository.findOneById(parentId))){
//            commentDTOS.add(commentConverter.toDTO(commentEntity));
//        }
//        return commentDTOS;
//    }

    @Override
    public List<CommentDTO> findByProductId(Integer productId) {
        List<CommentDTO> commentDTOS = new ArrayList<>();
        for (CommentEntity commentEntity: commentRepository.findByProductId(productId)){
            commentDTOS.add(commentConverter.toDTO(commentEntity));
        }
        return commentDTOS;
    }

    @Override
    public void delete(CommentDTO commentDTO) {
        commentRepository.deleteByIds(commentDTO.getIds());
    }


}
