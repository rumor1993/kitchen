package com.rumor.kitchen.boards.domain.board;

import com.rumor.kitchen.boards.domain.tag.Tag;
import com.rumor.kitchen.enumeration.Category;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
public class Board {
    private Long id;
    private String title;
    private Category category;
    private String description;
    private String contents;
    private Set<Tag> tags;
    private Long creator;
    private Long viewCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Board(Long id, String title, Category category, String description, String contents, Set<Tag> tags, Long creator, Long viewCount, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.description = description;
        this.contents = contents;
        this.tags = tags;
        this.creator = creator;
        this.viewCount = viewCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
