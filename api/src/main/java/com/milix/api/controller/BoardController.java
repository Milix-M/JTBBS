package com.milix.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import com.milix.api.service.BoardService;
import com.milix.api.service.dto.BoardDto;
import com.milix.api.service.dto.HttpResponseDto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
}
