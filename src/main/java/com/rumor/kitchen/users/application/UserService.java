package com.rumor.kitchen.users.application;

import com.rumor.kitchen.users.domain.User;
import com.rumor.kitchen.users.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void createUser(User user) {
        Optional<User> userEntity = userRepository.findBySubject(user.getSubject());

        if (!userEntity.isPresent()) {
            userRepository.save(user);
        }
    }

}
