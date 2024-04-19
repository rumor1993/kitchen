package com.rumor.kitchen.boards.infrastracture.board;

import com.rumor.kitchen.boards.domain.board.BoardRepository;
import com.rumor.kitchen.boards.presentation.board.response.BoardView;
import com.rumor.kitchen.files.FileRepository;
import com.rumor.kitchen.users.domain.UserRepository;
import com.rumor.kitchen.users.presentation.UserView;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepository {
    private final UserRepository userRepository;
    private final BoardJpaRepository boardJpaRepository;
    private final FileRepository fileRepository;

    @Override
    public List<BoardView> findAll() {
        return boardJpaRepository.findAll(Sort.by(Sort.Order.desc("createdAt"))).stream()
                .map(board -> new BoardView(
                        board.getId(),
                        board.getTitle(),
                        board.getCategory(),
                        board.getDescription(),
                        board.getContents(),
                        userRepository.findById(board.getCreator())
                                .map(user -> new UserView(user.getId(), user.getName(), user.getName()))
                                .orElse(null),
                        board.getViewCount(),
                        board.getCreatedAt(),
                        board.getUpdatedAt(),
                        fileRepository.findByBoardId(board.getId()).stream()
                                .map(file -> file.getFilePath())
                                .collect(Collectors.toList())
                ))
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
                        userRepository.findById(board.getCreator())
                                .map(user -> new UserView(user.getId(), user.getName(), user.getName()))
                                .orElse(null),
                        board.getViewCount(), board.getCreatedAt(), board.getUpdatedAt(),
                        fileRepository.findByBoardId(board.getId()).stream()
                                .map(file -> file.getFilePath())
                                .collect(Collectors.toList())
                ));
    }

    @Override
    public BoardEntity save(BoardEntity entity) {
        return boardJpaRepository.save(entity);
    }
}
