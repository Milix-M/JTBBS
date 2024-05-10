package com.milix.api.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BoardDto {

    private String id;

    private String name;

    private String createdAt;

    private String updatedAt;

    private List<CommentDto> comments;
}
