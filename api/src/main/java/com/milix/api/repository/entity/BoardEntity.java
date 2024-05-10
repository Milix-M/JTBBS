package com.milix.api.repository.entity;

import java.util.List;

import com.milix.api.repository.entity.common.CommonEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * ボードのエンティティ.
 */
@Entity
@Table(name = "boards")
@Getter
@Setter
public class BoardEntity extends CommonEntity {

    /* ボードID */
    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer boardId;

    /* ボードの名前 */
    @Column(name = "board_name")
    private String boardName;

    /* Comments */
    @OneToMany(mappedBy = "board")
    private List<CommentEntity> comments;
}
