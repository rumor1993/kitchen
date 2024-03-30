package com.rumor.kitchen.boards.infrastracture.board;

import com.rumor.kitchen.boards.domain.board.Board;
import com.rumor.kitchen.boards.infrastracture.tag.TagMapper;

import java.util.stream.Collectors;

public class BoardMapper {

    public static BoardEntity toEntity(Board board) {
        return new BoardEntity(
                board.getId(),
                board.getTitle(),
                board.getCategory(),
                board.getDescription(),
                board.getContents(),
                board.getTags().stream()
                        .map(tag -> TagMapper.toEntity(tag))
                        .collect(Collectors.toSet()),
                board.getCreator(),
                board.getViewCount());
    }

    public static Board toDomain(BoardEntity boardEntity) {
        return new Board(boardEntity.getId(),
                boardEntity.getTitle(),
                boardEntity.getCategory(),
                boardEntity.getDescription(),
                boardEntity.getContents(),
                boardEntity.getTags().stream()
                        .map(tagEntity -> TagMapper.toDomain(tagEntity))
                        .collect(Collectors.toSet()),
                boardEntity.getCreator(),
                boardEntity.getViewCount(),
                boardEntity.getCreatedAt(),
                boardEntity.getUpdatedAt()
        );
    }
}
