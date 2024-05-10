package com.milix.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milix.api.repository.CommentRepository;
import com.milix.api.repository.entity.CommentEntity;
import com.milix.api.service.dto.CommentDto;

import jakarta.transaction.Transactional;

/**
 * コメントの操作をするクラス.
 */
@Service
@Transactional
public class CommentService {

    /**
     * DI.
     */
    @Autowired
    private CommentRepository commentRepository;

    /**
     * コメントをidから探します.
     *
     * @param commentId 探すコメント.
     * @return コメントオブジェクト
     */
    public CommentDto getCommentById(String commentId) {
        CommentEntity entity = commentRepository.findById(Integer.parseInt(commentId)).get();
        var comment = new CommentDto();
        copyEntityToBean(entity, comment);
        return comment;
    }

    /**
     * コメントをListで全て返します
     *
     * @return コメントのリスト
     */
    public List<CommentDto> getCommentList() {
        List<CommentDto> comments = new ArrayList<>();
        List<CommentEntity> commentEntityList = commentRepository.findAll();
        commentEntityList.forEach(entity -> {
            var comment = new CommentDto();
            copyEntityToBean(entity, comment);
            comments.add(comment);
        });
        return comments;
    }

    /**
     * コメントを新しく作成します.
     *
     * @param commentDto 新しく作るコメント
     * @return 作成したコメント
     */
    public CommentDto createCommentDto(CommentDto commentDto) {
        CommentEntity entity = new CommentEntity();
        copyBeanToEntityForInsert(commentDto, entity);
        CommentEntity createdEntity = commentRepository.save(entity);
        CommentDto newCommentDto = new CommentDto();
        copyEntityToBean(createdEntity, newCommentDto);
        return newCommentDto;
    }

    /**
     * コメントをアップデートします
     *
     * @param comment アップデートするコメント オブジェクト.
     * @return アップデート後のコメント
     */
    public CommentDto updateCommentDto(CommentDto comment) {
        // FIX ME: 返却されるcreatedAtとupdatedAtの値がおかしい場合があるので直す.
        CommentEntity existingEntity = commentRepository.findById(Integer.parseInt(comment.getId())).get();
        copyBeanToEntityForUpdate(comment, existingEntity);
        var updatedEntity = commentRepository.save(existingEntity);
        CommentDto updatedCommentDto = new CommentDto();
        copyEntityToBean(updatedEntity, updatedCommentDto);
        return updatedCommentDto;
    }

    /**
     * 引数にしていしたコメントを削除します
     *
     * @param commentDtoId 削除するコメントのid.
     * @return 実行結果.
     */
    public boolean deleteCommentDtoById(String commentDtoId) {
        try {
            commentRepository.deleteById(Integer.parseInt(commentDtoId));
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    private void copyEntityToBean(CommentEntity entity, CommentDto comment) {
        comment.setId(String.valueOf(entity.getCommentId()));
        comment.setHandleName(entity.getHandleName());
        comment.setCommentOrderNumber(String.valueOf(entity.getCommentOrderNumber()));
        comment.setCommentText(entity.getCommentText());
        comment.setCreatedAt(String.valueOf(entity.getCreatedAt()));
        comment.setUpdatedAt(String.valueOf(entity.getUpdatedAt()));
    }

    private void copyBeanToEntityForInsert(CommentDto comment, CommentEntity entity) {
        if (!"".equals(comment.getHandleName())) {
            entity.setHandleName(comment.getHandleName());
        }
        // if (!"".equals(comment.getCommentOrderNumber())) {
        //     entity.setCommentOrderNumber(Integer.parseInt(comment.getCommentOrderNumber()));
        // }
        if (!"".equals(comment.getCommentText())) {
            entity.setCommentText(comment.getCommentText());
        }
    }

    private void copyBeanToEntityForUpdate(CommentDto comment, CommentEntity entity) {
        entity.setCommentId(Integer.parseInt(comment.getId()));
        copyBeanToEntityForInsert(comment, entity);
    }
}
