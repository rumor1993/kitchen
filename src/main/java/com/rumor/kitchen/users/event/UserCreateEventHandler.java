package com.rumor.kitchen.users.event;

import com.rumor.kitchen.users.application.UserService;
import com.rumor.kitchen.users.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class UserCreateEventHandler {
    private final UserService userService;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void handle(UserCreateEvent event) {
        User user = new User(event.subject(), event.email());
        userService.createUser(user);
    }
}
