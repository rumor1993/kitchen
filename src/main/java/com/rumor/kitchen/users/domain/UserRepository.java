package com.rumor.kitchen.users.domain;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface UserRepository extends Repository<User, Long> {

    Optional<User> findById(Long id);

    Optional<User> findBySubject(String subject);

    User save(User user);

    Optional<User> findByName(String name);
}
