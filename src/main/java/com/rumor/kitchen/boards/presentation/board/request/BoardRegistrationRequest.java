package com.rumor.kitchen.boards.presentation.board.request;

import com.rumor.kitchen.boards.application.board.dto.BoardRegistrationDto;
import com.rumor.kitchen.enumeration.Category;

import java.util.List;

public record BoardRegistrationRequest(
        String title,
        Category category,
        List<String> tags,
        String description,
        String contents
) {
    public BoardRegistrationDto toDto() {
        return new BoardRegistrationDto(title, category, tags, description, contents);
    }
}
