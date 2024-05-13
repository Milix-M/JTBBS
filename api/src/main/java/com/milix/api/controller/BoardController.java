package com.milix.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import com.milix.api.service.BoardService;
import com.milix.api.service.dto.BoardDto;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * ボードのコントローラー.
 */
@RestController
public class BoardController {

    /**
     * DI.
     */
    @Autowired
    private BoardService boardService;

    /**
     * ボードの情報を取得します.
     *
     * @param boardId 情報を取得したいボードのId.
     * @return 取得結果のBoardDtoオブジェクト.
     */
    @GetMapping("/api/board/{boardId}")
    public BoardDto getBoardById(@PathVariable("boardId") String boardId) {
        BoardDto board = boardService.getBoardById(boardId);
        return board;
    }

    /**
     * 現在存在するボード一覧を返します.
     *
     * @return ボードのリスト.
     */
    @GetMapping("/api/board")
    public List<BoardDto> getBoardList() {
        List<BoardDto> boards = boardService.getBoardList();
        return boards;
    }

    /**
     * 新しくボードを作成します.
     *
     * @param board BoardDto オブジェクト.
     * @return 作成したBoardDto オブジェクト.
     */
    @PostMapping("/api/board")
    @ResponseStatus(HttpStatus.CREATED)
    public BoardDto createBoard(@RequestBody BoardDto board) {
        BoardDto newBoard = boardService.createBoard(board);
        return newBoard;
    }

    /**
     * ボードの情報を更新します.
     *
     * @param boardid 更新するボードのid.
     * @param board   更新したいボード情報が入った BoardDto オブジェクト.
     * @return 更新したBoardDtoオブジェクト.
     */
    @PutMapping("/api/board/{boardId}")
    @ResponseStatus(HttpStatus.CREATED)
    public BoardDto updateBoard(@PathVariable("boardId") String boardid, @RequestBody BoardDto board) {
        board.setId(boardid);
        BoardDto updatedBoard = boardService.updateBoard(board);
        return updatedBoard;
    }

    /**
     * 指定したボードを削除します
     *
     * @param boardId 削除したいボードのid.
     */
    @DeleteMapping("/api/board/{boardId}")
    public void deleteBoard(@PathVariable("boardId") String boardId) {
        boardService.deleteBoardById(boardId);
    }
}
