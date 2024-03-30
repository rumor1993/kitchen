package com.rumor.kitchen.boards.application.board;

import com.rumor.kitchen.boards.application.board.dto.BoardRegistrationDto;
import com.rumor.kitchen.boards.domain.board.BoardRepository;
import com.rumor.kitchen.boards.infrastracture.board.BoardEntity;
import com.rumor.kitchen.boards.presentation.board.response.BoardView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardView getBoard(final Long id) {
        return boardRepository.findViewById(id)
                .orElseThrow(() -> new RuntimeException("NOT FOUND BOARD"));
    }

    public List<BoardView> getBoards() {
        return boardRepository.findAll();
    }

    public Long register(final BoardRegistrationDto boardRegistrationDto) {
        BoardEntity boardEntity = new BoardEntity(boardRegistrationDto.title(), boardRegistrationDto.category(), boardRegistrationDto.description(), boardRegistrationDto.contents(), null, "", 0L);
        return boardRepository.save(boardEntity).getId();
    }

    public void delete(Long id) {
        BoardEntity foundBoardEntity = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("NOT FOUND BOARD ID"));

        boardRepository.delete(foundBoardEntity);
    }
}
