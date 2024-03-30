package com.rumor.kitchen.boards.presentation.board;

import com.rumor.kitchen.boards.application.board.BoardService;
import com.rumor.kitchen.boards.presentation.board.request.BoardRegistrationRequest;
import com.rumor.kitchen.boards.presentation.board.response.BoardView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping
    public List<BoardView> boards() {
        return boardService.getBoards();
    }

    @GetMapping("{id}")
    public BoardView board(@PathVariable final Long id) {
        return boardService.getBoard(id);
    }

    @PostMapping
    public ResponseEntity<Long> register(@RequestBody final BoardRegistrationRequest boardRegistrationRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(boardService.register(boardRegistrationRequest.toDto()));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable final Long id) {
        boardService.delete(id);
    }
}
