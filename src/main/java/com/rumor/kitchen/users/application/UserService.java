package com.rumor.kitchen.users.application;

import com.rumor.kitchen.users.domain.Jwt;
import com.rumor.kitchen.users.domain.User;
import com.rumor.kitchen.users.domain.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final Jwt jwt;
    private final UserRepository userRepository;

    public void createUser(User user) {
        Optional<User> userEntity = userRepository.findBySubject(user.getSubject());

        if (!userEntity.isPresent()) {
            userRepository.save(user);
        }
    }

    public String rename(Long id, String name) {
        if (userRepository.findByName(name).isPresent()) {
            new RuntimeException("이미 존재하는 Name 입니다.");
        }

        User userEntity = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID는 존재하지 않습니다."));

        userEntity.rename(name);
        return jwt.createToken(userEntity);
    }
}
