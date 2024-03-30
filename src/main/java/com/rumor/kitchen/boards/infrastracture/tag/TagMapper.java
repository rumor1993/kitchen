package com.rumor.kitchen.boards.infrastracture.tag;

import com.rumor.kitchen.boards.domain.tag.Tag;
import com.rumor.kitchen.boards.infrastracture.board.BoardMapper;

import java.util.stream.Collectors;

public class TagMapper {

    public static TagEntity toEntity(Tag domain) {
        return new TagEntity(domain.getId(), domain.getName(), domain.getBoards().stream()
                .map(board -> BoardMapper.toEntity(board))
                .collect(Collectors.toSet()));
    }

    public static Tag toDomain(TagEntity entity) {
        return new Tag(entity.getId(), entity.getName(), entity.getBoards().stream()
                .map(boardEntity -> BoardMapper.toDomain(boardEntity))
                .collect(Collectors.toSet()));
    }
}
