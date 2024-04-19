package com.rumor.kitchen.files;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface FileRepository extends Repository<FileEntity, Long> {
    Optional<FileEntity> findById(Long id);
    List<FileEntity> findByUserId(Long id);
    List<FileEntity> findByBoardId(Long id);

    List<FileEntity> findByUserIdAndBoardId(Long userId, Long boardId);
    FileEntity save(FileEntity file);
}
