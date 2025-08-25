package com.simple_official_spring_template.api;

import com.simple_official_spring_template.dto.CommentDTO;
import com.simple_official_spring_template.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentAPI {
    @Autowired
    private ICommentService commentService;

    @GetMapping(value = "/api-comment/{id}")
    public CommentDTO getCommentById(@PathVariable(value = "id", required = true) Integer id){
        return commentService.findOneById(id);
    }

    @GetMapping(value = "/api-comment")
    public List<CommentDTO> getComments(@RequestParam(value = "productId", required = false) Integer productId){
        if (productId != null){
            return commentService.findByProductId(productId);
        }
        return commentService.findAll();
    }


    @PostMapping(value = "/api-comment")
    public CommentDTO createComment(@RequestBody CommentDTO commentDTO){
        return commentService.save(commentDTO);
    }

    @PutMapping(value = "/api-comment")
    public CommentDTO updateComment(@RequestBody CommentDTO commentDTO){
        return commentService.update(commentDTO);
    }

    @DeleteMapping(value = "/api-comment")
    public void deleteComments(@RequestBody CommentDTO commentDTO){
        commentService.delete(commentDTO);
    }
}
