package com.rumor.kitchen.boards.presentation.board.response;

import com.rumor.kitchen.users.presentation.UserView;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
public class BoardView {
    private Long id;
    private String Title;
    private com.rumor.kitchen.enumeration.Category Category;
    private String Description;
    private String Contents;
    private UserView Creator;
    private Long viewCount;

    private Long createdAt;
    private Long updatedAt;

    public BoardView(Long id, String title, com.rumor.kitchen.enumeration.Category category, String description, String contents, UserView creator, Long viewCount, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.Title = title;
        this.Category = category;
        this.Description = description;
        this.Contents = contents;
        this.Creator = creator;
        this.viewCount = viewCount;
        this.createdAt = createdAt.atZone(ZoneId.of("Asia/Seoul")).toInstant().toEpochMilli();
        this.updatedAt = updatedAt.atZone(ZoneId.of("Asia/Seoul")).toInstant().toEpochMilli();
    }
}
