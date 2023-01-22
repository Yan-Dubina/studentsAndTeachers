package com.example.shop.mappers;

import com.example.shop.domain.Comment;
import com.example.shop.dto.CommentDTO;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public CommentDTO domainToDTO(Comment comment) {
        return CommentDTO.builder()
                .date(comment.getDate())
                .description(comment.getDescription())
                .shortDescription(comment.getShortDescription())
                .rate(comment.getRate())
                .build();
    }
}
