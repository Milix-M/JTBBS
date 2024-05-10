package com.milix.api.repository.entity;

import com.milix.api.repository.entity.common.CommonEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * コメントのエンティティ
 */
@Entity
@Table(name = "comments")
@Getter
@Setter
public class CommentEntity extends CommonEntity {

    /* コメントID */
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer commentId;

    /* ハンドルネーム */
    @Column(name = "handle_name")
    private String handleName;

    /* コメント本文 */
    @Column(name = "comment_text", nullable = false)
    private String commentText;

    /* ボードid(FK) */
    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private BoardEntity board;
}
