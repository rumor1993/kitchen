package com.rumor.kitchen.users.presentation;

import com.rumor.kitchen.config.resolver.Login;
import com.rumor.kitchen.users.application.UserService;
import com.rumor.kitchen.users.domain.Jwt;
import com.rumor.kitchen.users.domain.LoginUser;
import com.rumor.kitchen.users.presentation.request.EditUserRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public void myProfile(@Login final LoginUser loginUser) {

    }

    @PostMapping("/{id}/name")
    public ResponseEntity<String> rename(@PathVariable final Long id, @RequestBody final EditUserRequest editRequest) {
        return ResponseEntity.ok().body(userService.rename(id, editRequest.name()));
    }
}
