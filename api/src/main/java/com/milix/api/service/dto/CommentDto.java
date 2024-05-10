package com.milix.api.service.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentDto {

    private String id;

    private String handleName;

    private String commentText;

    private String boardId;

    private String createdAt;

    private String updatedAt;
}
