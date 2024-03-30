package com.rumor.kitchen.boards.presentation.board.response;

import lombok.Getter;
@Getter
public class BoardView {
    private Long id;
    private String Title;
    private com.rumor.kitchen.enumeration.Category Category;
    private String Description;
    private String Contents;
    private String Creator;
    private Long ViewCount;

    public BoardView(Long id, String title, com.rumor.kitchen.enumeration.Category category, String description, String contents, String creator, Long viewCount) {
        this.id = id;
        Title = title;
        Category = category;
        Description = description;
        Contents = contents;
        Creator = creator;
        ViewCount = viewCount;
    }
}
