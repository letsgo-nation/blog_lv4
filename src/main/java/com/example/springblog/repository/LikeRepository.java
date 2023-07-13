package com.example.springblog.repository;

import com.example.springblog.entity.Comment;
import com.example.springblog.entity.Like;
import com.example.springblog.entity.Post;
import com.example.springblog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    // User, Post에서 단건 조회하기
     Optional<Like> findByUserAndPost(User user, Post post);
    // User, Comment에서 단건 조회하기
     Optional<Like> findByUserAndComment(User user, Comment comment);
}

