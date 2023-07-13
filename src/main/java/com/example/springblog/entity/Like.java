package com.example.springblog.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "likes") // like 테이블 생성
public class Like {

    @Id // user별 ID
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // post_id 관계도, 다대일
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    // user_id 관계도, 다대일
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // comment_id 관계도, 다대일
    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    // like 요청 시, user, post 요청
    public Like(User user, Post post) {
        setUser(user);
        setPost(post);
    }

    public Like(User user, Comment comment) {
        setUser(user);
        setComment(comment);
    }

    // setUser 메서드 생성
    public void setUser(User user) {
        this.user = user;
    }

    // setPost 메서드 생성
    public void setPost(Post post) {
        this.post = post;
    }

    // setComment 메서드 생성
    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
