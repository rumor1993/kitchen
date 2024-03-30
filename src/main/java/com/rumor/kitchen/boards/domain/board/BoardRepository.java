package com.rumor.kitchen.boards.domain.board;

import com.rumor.kitchen.boards.infrastracture.board.BoardEntity;
import com.rumor.kitchen.boards.presentation.board.response.BoardView;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
     Optional<BoardEntity> findById(Long id);
     Optional<BoardView> findViewById(Long id);
     <S extends BoardEntity> S save(BoardEntity entity);
     List<BoardView> findAll();
     void delete(BoardEntity boardEntity);
}
