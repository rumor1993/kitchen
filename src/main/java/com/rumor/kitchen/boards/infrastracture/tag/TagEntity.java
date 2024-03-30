package com.rumor.kitchen.boards.infrastracture.tag;

import com.rumor.kitchen.boards.infrastracture.board.BoardEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Table(name = "tag")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "tags")
    private Set<BoardEntity> boards = new HashSet<>();

    public TagEntity(Long id, String name, Set<BoardEntity> boards) {
        this.id = id;
        this.name = name;
        this.boards = boards;
    }
}
