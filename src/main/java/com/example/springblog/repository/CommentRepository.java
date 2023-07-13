package com.example.springblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springblog.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
