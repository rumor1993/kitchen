package com.rumor.kitchen.boards.application.board;

import com.rumor.kitchen.boards.application.board.dto.BoardRegistrationDto;
import com.rumor.kitchen.boards.domain.board.BoardRepository;
import com.rumor.kitchen.boards.infrastracture.board.BoardEntity;
import com.rumor.kitchen.boards.presentation.board.response.BoardView;
import com.rumor.kitchen.files.FileEntity;
import com.rumor.kitchen.files.FileRepository;
import com.rumor.kitchen.users.domain.LoginUser;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {
    private final BoardRepository boardRepository;
    private final FileRepository fileRepository;

    public BoardView getBoard(final Long id) {
        return boardRepository.findViewById(id)
                .orElseThrow(() -> new RuntimeException("NOT FOUND BOARD"));
    }

    public List<BoardView> getBoards() {
        return boardRepository.findAll();
    }

    public Long register(final BoardRegistrationDto boardRegistrationDto, final LoginUser loginUser) {
        BoardEntity boardEntity = new BoardEntity(boardRegistrationDto.title(), boardRegistrationDto.category(), boardRegistrationDto.description(), boardRegistrationDto.contents(), null, loginUser.getId(), 0L);
        BoardEntity savedBoardEntity = boardRepository.save(boardEntity);

        boardRegistrationDto.filePaths().stream()
                .forEach(path -> fileRepository.save(new FileEntity(loginUser.getId(), savedBoardEntity.getId(), path)));

        return savedBoardEntity.getId();
    }

    public void delete(Long id) {
        BoardEntity foundBoardEntity = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("NOT FOUND BOARD ID"));

        boardRepository.delete(foundBoardEntity);
    }
}
