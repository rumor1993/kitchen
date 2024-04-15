package com.rumor.kitchen.users.domain;

import com.rumor.kitchen.boards.infrastracture.common.AuditableEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String subject;

    private String name;

    public User(String subject, String name) {
        this.subject = subject;
        this.name = name;
    }

    public void rename(String name) {
        this.name = name;
    }
}
