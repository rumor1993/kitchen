package com.rumor.kitchen.files;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;

    public Path saveAndGetPath(MultipartFile file) throws IOException {
        return saveAndGetPath("common" ,file);
    }

    public Path saveAndGetPath(String directoryName, MultipartFile file) throws IOException {
        Path directory = Paths.get("data", "kitchen", directoryName);
        Path directoryAbsolute = directory.toAbsolutePath().normalize();
        Files.createDirectories(directory);

        String fileExtension = com.google.common.io.Files.getFileExtension(file.getOriginalFilename());
        String fileName = UUID.randomUUID() + "." + fileExtension;
        Path targetPath = directoryAbsolute.resolve(fileName).normalize();

        file.transferTo(targetPath);
        return directory.resolve(fileName);
    }
}
