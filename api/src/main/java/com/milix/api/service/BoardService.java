package com.milix.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milix.api.repository.BoardRepository;
import com.milix.api.repository.entity.BoardEntity;
import com.milix.api.service.dto.BoardDto;

import jakarta.transaction.Transactional;

/**
 * ボードの操作をするClass.
 */
@Service
@Transactional
public class BoardService {

    /**
     * DI.
     */
    @Autowired
    private BoardRepository boardRepository;

    /**
     * ボードをidから探すメソッド
     *
     * @param boardId 探すボードのid.
     * @return ボード インスタンス
     */
    public BoardDto getBoardById(String boardId) {
        BoardEntity entity = boardRepository.findById(Integer.parseInt(boardId)).get();
        var board = new BoardDto();
        copyEntityToBean(entity, board);
        return board;
    }

    /**
     * ボードをListで全て返します
     *
     * @return ボードのリスト
     */
    public List<BoardDto> getBoardList() {
        List<BoardDto> boards = new ArrayList<>();
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        boardEntityList.forEach(entity -> {
            var board = new BoardDto();
            copyEntityToBean(entity, board);
            boards.add(board);
        });
        return boards;
    }

    /**
     * 新しくボードを作成します
     *
     * @param board 新しく作るボード
     * @return 作成後のボード
     */
    public BoardDto createBoard(BoardDto board) {
        var entity = new BoardEntity();
        copyBeanToEntityForInsert(board, entity);
        var createdEntity = boardRepository.save(entity);
        var newBoard = new BoardDto();
        copyEntityToBean(createdEntity, newBoard);
        return newBoard;
    }

    /**
     * ボードの情報をアップデートします
     *
     * @param board アップデートするボード
     * @return アップデート後のボード
     */
    public BoardDto updateBoard(BoardDto board) {
        var entity = new BoardEntity();
        copyBeanToEntityForUpdate(board, entity);
        var updatedEntity = boardRepository.save(entity);
        var updatedBoard = new BoardDto();
        copyEntityToBean(updatedEntity, updatedBoard);
        return updatedBoard;
    }

    /**
     * 引数に指定したボードを削除します
     *
     * @param boardId 削除するボードのid.
     * @return 成功: true 失敗: false
     */
    public boolean deleteBoardById(String boardId) {
        try {
            boardRepository.deleteById(Integer.parseInt(boardId));
        } catch (IllegalArgumentException e) {
            return false;
        }

        return true;
    }

    private void copyEntityToBean(BoardEntity entity, BoardDto board) {
        board.setId(String.valueOf(entity.getBoardId()));
        board.setName(entity.getBoardName());
        board.setCreatedAt(String.valueOf(entity.getCreatedAt()));
        board.setUpdatedAt(String.valueOf(entity.getUpdatedAt()));
    }

    private void copyBeanToEntityForInsert(BoardDto board, BoardEntity entity) {
        if (!"".equals(board.getName())) {
            entity.setBoardName(board.getName());
        }
    }

    private void copyBeanToEntityForUpdate(BoardDto board, BoardEntity entity) {
        entity.setBoardId(Integer.parseInt(board.getId()));
        copyBeanToEntityForInsert(board, entity);
    }
}
