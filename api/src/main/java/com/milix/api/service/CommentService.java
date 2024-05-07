package com.milix.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
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
     * @param CommentDto 新しく作るコメント
     * @return 作成したコメント
     */
    public CommentDto createCommentDto(CommentDto CommentDto) {
        CommentEntity entity = new CommentEntity();
        copyBeanToEntityForInsert(CommentDto, entity);
        CommentEntity createdEntity = commentRepository.save(entity);
        CommentDto newCommentDto = new CommentDto();
        copyEntityToBean(createdEntity, newCommentDto);
        return newCommentDto;
    }

    /**
     * コメントをアップデートします
     * @param CommentDto アップデートするコメント　オブジェクト.
     * @return アップデート後のコメント
     */
    public CommentDto updateCommentDto(CommentDto CommentDto) {
        CommentEntity entity = new CommentEntity();
        copyBeanToEntityForUpdate(CommentDto, entity);
        CommentEntity updatedEntity = commentRepository.save(entity);
        CommentDto updatedCommentDto = new CommentDto();
        copyEntityToBean(updatedEntity, updatedCommentDto);
        return updatedCommentDto;
    }

    /**
     * 引数にしていしたコメントを削除します
     * @param CommentDtoId 削除するコメントのid.
     * @return 実行結果.
     */
    public boolean deleteCommentDtoById(String CommentDtoId) {
        try {
            commentRepository.deleteById(Integer.parseInt(CommentDtoId));
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    private void copyEntityToBean(CommentEntity entity, CommentDto comment) {
        BeanUtils.copyProperties(entity, comment);
    }

    private void copyBeanToEntityForInsert(CommentDto comment, CommentEntity entity) {
        if (!"".equals(comment.getHandleName())) {
            entity.setHandleName(comment.getHandleName());
        }
        if (!"".equals(comment.getCommentOrderNumber())) {
            entity.setCommentOrderNumber(Integer.parseInt(comment.getCommentOrderNumber()));
        }
        if (!"".equals(comment.getCommentText())) {
            entity.setCommentText(comment.getCommentText());
        }
    }

    private void copyBeanToEntityForUpdate(CommentDto comment, CommentEntity entity) {
        entity.setCommentId(Integer.parseInt(comment.getId()));
        copyBeanToEntityForInsert(comment, entity);
    }
}
