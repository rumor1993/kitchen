package com.rumor.kitchen.boards.domain.tag;

import com.rumor.kitchen.boards.domain.board.Board;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class Tag {
    private Long id;
    private String name;
    private Set<Board> boards = new HashSet<>();

    public Tag(Long id, String name, Set<Board> boards) {
        this.id = id;
        this.name = name;
        this.boards = boards;
    }
}
