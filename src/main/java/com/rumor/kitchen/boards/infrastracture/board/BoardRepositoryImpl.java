package com.rumor.kitchen.boards.infrastracture.board;

import com.rumor.kitchen.boards.domain.board.BoardRepository;
import com.rumor.kitchen.boards.presentation.board.response.BoardView;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepository {
    private final BoardJpaRepository boardJpaRepository;

    @Override
    public List<BoardView> findAll() {
        return boardJpaRepository.findAll(Sort.by(Sort.Order.desc("createdAt"))).stream()
                .map(board -> new BoardView(
                        board.getId(),
                        board.getTitle(),
                        board.getCategory(),
                        board.getDescription(),
                        board.getContents(),
                        board.getCreator(),
                        board.getViewCount()))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(BoardEntity boardEntity) {
        boardJpaRepository.delete(boardEntity);
    }

    @Override
    public Optional<BoardEntity> findById(Long id) {
        return boardJpaRepository.findById(id);
    }

    @Override
    public Optional<BoardView> findViewById(Long id) {
        return boardJpaRepository.findById(id)
                .map(board -> new BoardView(
                        board.getId(),
                        board.getTitle(),
                        board.getCategory(),
                        board.getDescription(),
                        board.getContents(),
                        board.getCreator(),
                        board.getViewCount()));
    }

    @Override
    public BoardEntity save(BoardEntity entity) {
        return boardJpaRepository.save(entity);
    }
}
