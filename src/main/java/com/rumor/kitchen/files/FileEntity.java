package com.rumor.kitchen.files;

import com.rumor.kitchen.boards.infrastracture.common.AuditableEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "file")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FileEntity extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long boardId;

    private String filePath;

    public FileEntity(Long userId, Long boardId, String filePath) {
        this.userId = userId;
        this.boardId = boardId;
        this.filePath = filePath;
    }
}
