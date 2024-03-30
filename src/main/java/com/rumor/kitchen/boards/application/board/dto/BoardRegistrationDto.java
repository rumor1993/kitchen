package com.rumor.kitchen.boards.application.board.dto;

import com.rumor.kitchen.enumeration.Category;

import java.util.List;

public  record BoardRegistrationDto(
        String title,
        Category category,
        List<String> tags,
        String description,
        String contents
) {

}