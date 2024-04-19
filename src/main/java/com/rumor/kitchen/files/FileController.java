package com.rumor.kitchen.files;


import com.rumor.kitchen.config.resolver.Login;
import com.rumor.kitchen.users.domain.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> upload(@Login LoginUser loginUser, @RequestPart MultipartFile file) throws IOException {
        return ResponseEntity.ok().body("/" + fileService.saveAndGetPath(loginUser.getId().toString(), file).toString());
    }
}
