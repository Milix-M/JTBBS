package com.milix.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import com.milix.api.service.BoardService;
import com.milix.api.service.dto.BoardDto;
import com.milix.api.service.dto.HttpResponseDto;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/api/board/{boardId}")
    public HttpResponseDto getBoardById(@PathVariable("boardId") String boardId) {
        var httpResponseDto = new HttpResponseDto();
        BoardDto board = boardService.getBoardById(boardId);
        httpResponseDto.setHttpStatus(HttpStatus.OK);
        httpResponseDto.setResponseData(board);
        return httpResponseDto;
    }

    @GetMapping("/api/board")
    public HttpResponseDto getBoardList() {
        var httpResponseDto = new HttpResponseDto();
        List<BoardDto> boards = boardService.getBoardList();
        httpResponseDto.setHttpStatus(HttpStatus.OK);
        httpResponseDto.setResponseData(boards);
        return httpResponseDto;
    }

    @PostMapping("/api/board")
    public HttpResponseDto createBoard(@RequestBody BoardDto board) {
        var httpResponseDto = new HttpResponseDto();
        BoardDto newBoard = boardService.createBoard(board);
        httpResponseDto.setHttpStatus(HttpStatus.OK);
        httpResponseDto.setResponseData(newBoard);
        return httpResponseDto;
    }

    @PutMapping("/api/board/{boardId}")
    public HttpResponseDto updateBoard(@PathVariable("boardId") String boardid, @RequestBody BoardDto board) {
        var httpResponseDto = new HttpResponseDto();
        board.setId(boardid);
        BoardDto updatedBoard = boardService.updateBoard(board);
        httpResponseDto.setHttpStatus(HttpStatus.OK);
        httpResponseDto.setResponseData(updatedBoard);
        return httpResponseDto;
    }

    @DeleteMapping("/api/board/{boardId}")
    public HttpResponseDto deleteBoard(@PathVariable("boardId") String boardId) {
        var httpResponseDto = new HttpResponseDto();
        if (boardService.deleteBoardById(boardId)) {
            httpResponseDto.setHttpStatus(HttpStatus.OK);
            httpResponseDto.setMessage("delete ok.");
        } else {
            httpResponseDto.setHttpStatus(HttpStatus.BAD_REQUEST);
            httpResponseDto.setMessage("bad request.");
        }
        return httpResponseDto;
    }
}
