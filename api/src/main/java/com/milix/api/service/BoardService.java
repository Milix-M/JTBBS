package com.milix.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milix.api.repository.BoardRepository;
import com.milix.api.repository.entity.BoardEntity;
import com.milix.api.repository.entity.CommentEntity;
import com.milix.api.service.dto.BoardDto;
import com.milix.api.service.dto.CommentDto;

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
            // copyEntityToBean(entity, board);
            // N+1回避
            copyEntityToBeanLessComments(entity, board);
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
        // FIX ME: 返却されるcreatedAtとupdatedAtの値がおかしい場合があるので直す.
        BoardEntity existingEntity = boardRepository.findById(Integer.parseInt(board.getId())).get();
        copyBeanToEntityForUpdate(board, existingEntity);
        var updatedEntity = boardRepository.save(existingEntity);
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

        // コメントtest
        // nullじゃなければコメントをコピー
        // TODO: もっといい書き方できるはず
        if (entity.getComments() != null) {
            List<CommentDto> comments = new ArrayList<>();
            for (CommentEntity c : entity.getComments()) {
                var commentTmp = new CommentDto();
                commentTmp.setId(String.valueOf(c.getCommentId()));
                commentTmp.setHandleName(c.getHandleName());
                commentTmp.setCommentText(c.getCommentText());
                commentTmp.setUpdatedAt(String.valueOf(c.getUpdatedAt()));
                commentTmp.setCreatedAt(String.valueOf(c.getCreatedAt()));
                // 汚い
                commentTmp.setBoardId(String.valueOf(c.getBoard().getBoardId()));

                comments.add(commentTmp);
            }
            board.setComments(comments);
        }
    }

    // N+1問題回避用
    private void copyEntityToBeanLessComments(BoardEntity entity, BoardDto board) {
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
