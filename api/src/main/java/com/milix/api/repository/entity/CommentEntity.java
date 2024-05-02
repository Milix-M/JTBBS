package com.milix.api.repository.entity;

import com.milix.api.repository.entity.common.CommonEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "comments")
@Getter
@Setter
public class CommentEntity extends CommonEntity{

    /* コメントID */
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer commentId;

    @Column(name = "handle_name")
    private String handleName;

    @Column(name = "comment_order_number")
    private Integer commentOrderNumber;

    @Column(name = "comment_text")
    private String commentText;
}
