package com.rumor.kitchen.boards.infrastracture.like;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "like_board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LikeBoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long boardId;
    private Long likeUserId;
    private Long likedUserId;
}
