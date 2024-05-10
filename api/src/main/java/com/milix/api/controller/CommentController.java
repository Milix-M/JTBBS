package com.milix.api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import com.milix.api.service.CommentService;
import com.milix.api.service.dto.CommentDto;
import com.milix.api.service.dto.HttpResponseDto;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
     * @return CommentDtoがsetされたHttpResponceオブジェクト
     */
    @GetMapping("/api/comment/{commentId}")
    public HttpResponseDto getCommentById(@PathVariable("commentId") String commentId) {
        var httpResponseDto = new HttpResponseDto();
        CommentDto comment = commentService.getCommentById(commentId);
        httpResponseDto.setHttpStatus(HttpStatus.OK);
        httpResponseDto.setResponseData(comment);
        return httpResponseDto;
    }

    /**
     * 存在するコメント一覧を返します
     *
     * @return コメント一覧をセットしたhttpResponceDto.
     */
    @GetMapping("/api/comment")
    public HttpResponseDto getCommentList() {
        var httpResponseDto = new HttpResponseDto();
        List<CommentDto> comments = commentService.getCommentList();
        httpResponseDto.setHttpStatus(HttpStatus.OK);
        httpResponseDto.setResponseData(comments);
        return httpResponseDto;
    }

    //TODO: BoardIdとForeignKeyできるようにする
    /**
     * 新しくコメントを作成します.
     *
     * @param comment 作成したいデータの入ったCommentDtoオブジェクト.
     * @return 作成したCommentDtoがセットされたHttpResponceDtoオブジェクト.
     */
    @PostMapping("/api/comment")
    public HttpResponseDto createComment(@RequestBody CommentDto comment) {
        var httpResponseDto = new HttpResponseDto();
        CommentDto newComment = commentService.createCommentDto(comment);
        httpResponseDto.setHttpStatus(HttpStatus.CREATED);
        httpResponseDto.setResponseData(newComment);
        return httpResponseDto;
    }

    /**
     * 指定したコメントを更新します.
     *
     * @param commentId 更新したいコメントのid.
     * @param comment   更新したいコメントの情報が入ったCommentDtoオブジェクト
     * @return 更新後のCommentDtoがセットされたCommentDtoオブジェクト.
     */
    @PutMapping("/api/comment/{commentId}")
    public HttpResponseDto updateComment(@PathVariable("commentId") String commentId, @RequestBody CommentDto comment) {
        var httpResponseDto = new HttpResponseDto();
        comment.setId(commentId);
        CommentDto updatedComment = commentService.updateCommentDto(comment);
        httpResponseDto.setHttpStatus(HttpStatus.CREATED);
        httpResponseDto.setResponseData(updatedComment);
        return httpResponseDto;
    }

    /**
     * 指定したコメントを削除します
     *
     * @param commentId 削除するコメントのId.
     * @return 処理結果.
     */
    @DeleteMapping("/api/comment/{commentId}")
    public HttpResponseDto deleteComment(@PathVariable("commentId") String commentId) {
        var httpResponseDto = new HttpResponseDto();
        if (commentService.deleteCommentDtoById(commentId)) {
            httpResponseDto.setHttpStatus(HttpStatus.OK);
            httpResponseDto.setMessage("delete ok.");
        } else {
            httpResponseDto.setHttpStatus(HttpStatus.BAD_REQUEST);
            httpResponseDto.setMessage("bad request.");
        }
        return httpResponseDto;
    }
}
