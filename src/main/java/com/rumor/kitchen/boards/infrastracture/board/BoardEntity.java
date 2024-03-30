package com.rumor.kitchen.boards.infrastracture.board;

import com.rumor.kitchen.enumeration.Category;
import com.rumor.kitchen.boards.infrastracture.common.AuditableEntity;
import com.rumor.kitchen.boards.infrastracture.tag.TagEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Table(name = "board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardEntity extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Category category;
    private String description;
    private String contents;
    @ManyToMany
    @JoinTable(
            name = "board_tag",
            joinColumns = @JoinColumn(name = "board_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<TagEntity> tags = new HashSet<>();
    private String creator;
    private Long viewCount;

    public BoardEntity(String title, Category category, String description, String contents, Set<TagEntity> tags, String creator, Long viewCount) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.contents = contents;
        this.tags = tags;
        this.creator = creator;
        this.viewCount = viewCount;
    }

    public BoardEntity(Long id, String title, Category category, String description, String contents, Set<TagEntity> tags, String creator, Long viewCount) {
        this(title, category, description, contents, tags, creator, viewCount);
        this.id = id;
    }
}
