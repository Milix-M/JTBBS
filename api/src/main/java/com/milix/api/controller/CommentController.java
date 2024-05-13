package com.milix.api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import com.milix.api.service.CommentService;
import com.milix.api.service.dto.CommentDto;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * コメントのコントローラー.
 */
@RestController
public class CommentController {

    /**
     * DI.
     */
    @Autowired
    private CommentService commentService;

    /**
     * 指定したコメントの情報を返します.
     *
     * @param commentId 取得したいコメントのid.
     * @return CommentDtoオブジェクト
     */
    @GetMapping("/api/comment/{commentId}")
    public CommentDto getCommentById(@PathVariable("commentId") String commentId) {
        CommentDto comment = commentService.getCommentById(commentId);
        return comment;
    }

    /**
     * 存在するコメント一覧を返します
     *
     * @return コメント一覧.
     */
    @GetMapping("/api/comment")
    public List<CommentDto> getCommentList() {
        List<CommentDto> comments = commentService.getCommentList();
        return comments;
    }

    /**
     * 新しくコメントを作成します.
     *
     * @param comment 作成したいデータの入ったCommentDtoオブジェクト.
     * @return 作成したCommentDtoオブジェクト.
     */
    @PostMapping("/api/comment")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto createComment(@RequestBody CommentDto comment) {
        CommentDto newComment = commentService.createCommentDto(comment);
        return newComment;
    }

    /**
     * 指定したコメントを更新します.
     *
     * @param commentId 更新したいコメントのid.
     * @param comment   更新したいコメントの情報が入ったCommentDtoオブジェクト
     * @return 更新後のCommentDtoオブジェクト.
     */
    @PutMapping("/api/comment/{commentId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto updateComment(@PathVariable("commentId") String commentId, @RequestBody CommentDto comment) {
        comment.setId(commentId);
        CommentDto updatedComment = commentService.updateCommentDto(comment);
        return updatedComment;
    }

    /**
     * 指定したコメントを削除します
     *
     * @param commentId 削除するコメントのId.
     */
    @DeleteMapping("/api/comment/{commentId}")
    public void deleteComment(@PathVariable("commentId") String commentId) {
        commentService.deleteCommentDtoById(commentId);
    }
}
