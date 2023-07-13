package com.example.springblog.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "comment")
public class Comment extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String body;

    // comment 테이블에 likes 열을 포함. like의 수를 보여준다.
    @Column(name = "likes", nullable = false)
    private int likeCount = 0;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.REMOVE)
    private List<Like> likes;

    public Comment(String body) {
        this.body = body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    // Like를 요청하면 Like숫자가 올라감
    public void upLike() {
        this.likeCount++;
    }

    // Like를 요청하면 Like숫자가 내려감
    public void downLike() {
        this.likeCount--;
    }
}
