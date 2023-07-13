package com.example.springblog.service;

import com.example.springblog.dto.LikeResponseDto;
import com.example.springblog.entity.Comment;
import com.example.springblog.entity.Like;
import com.example.springblog.entity.Post;
import com.example.springblog.entity.User;
import com.example.springblog.jwt.JwtUtil;
import com.example.springblog.repository.CommentRepository;
import com.example.springblog.repository.LikeRepository;
import com.example.springblog.repository.PostRepository;
import com.example.springblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;
    private final JwtUtil jwtUtil;

    // 게시글 Like Api
    @Transactional
    // @Transactional: 업데이트 후 저장을 위해 사용
    public LikeResponseDto likePost(Long id, User user){

        // postRepository에서 user Id를 찾는 메소드
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
                // User가 좋아요를 누른 게시물이 존재하는지 확인. 존재하지 않을 경우, IllegalArgumentException를 던진다.
        );

        // Optional: 단 건 조회를 할 때 쓰인다.
        Optional<Like> checkUserAndPost = likeRepository.findByUserAndPost(user, post);
        // 좋아요를 누른 user, post를 찾는다.

        if(checkUserAndPost.isPresent()){
            return new LikeResponseDto("해당 게시글의 좋아요를 이미 눌렀습니다.", 400);
        } else {
            likeRepository.save(new Like(user, post));
            post.upLike(); // 좋아요 개수가 올라간다.
            return new LikeResponseDto("해당 게시글을 좋아합니다.", 200);
        }
    }

    // 게시글 Like 취소 Api
    @Transactional
    public LikeResponseDto deleteLikePost(Long id, User user) {
        // postRepository에서 user Id를 찾는 메소드
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
                // User가 좋아요를 누른 게시물이 존재하는지 확인. 존재하지 않을 경우, IllegalArgumentException를 던진다.
        );

        // Optional: 단 건 조회를 할 때 쓰인다.
        Optional<Like> checkUserAndPost = likeRepository.findByUserAndPost(user, post);
        // 좋아요를 누른 user, post를 찾는다.

        if(!checkUserAndPost.isPresent()){
            return new LikeResponseDto("해당 게시글의 좋아요가 존재하지 않습니다.", 400);
        } else {
            likeRepository.save(new Like(user, post));
            post.downLike(); //
            return new LikeResponseDto("해당 게시글의 좋아요가 취소되었습니다.", 200);
        }
    }

    // 댓글 Like Api
    @Transactional
    public LikeResponseDto likeComment(Long id, User user) {
        // postRepository에서 post를 찾는 메소드
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
                // User가 좋아요를 누른 댓글이 존재하는지 확인. 존재하지 않을 경우, IllegalArgumentException를 던진다.
        );

        // Optional: 단 건 조회를 할 때 쓰인다.
        Optional<Like> checkUserAndComment = likeRepository.findByUserAndComment(user, comment);
        // 좋아요를 누른 user, comment를 찾는다.

        if(checkUserAndComment.isPresent()){
            return new LikeResponseDto("해당 댓글의 좋아요를 이미 눌렀습니다.", 400);
        } else {
            likeRepository.save(new Like(user, comment));
            comment.upLike(); // 좋아요 개수가 올라간다.
            return new LikeResponseDto("해당 댓글을 좋아합니다.", 200);
        }
    }

    // 댓글 Like 취소 Api
    @Transactional
    public LikeResponseDto deleteLikeComment(Long id, User user) {
        // postRepository에서 댓글을 찾는 메소드
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
                // User가 좋아요를 누른 게시물이 존재하는지 확인. 존재하지 않을 경우, IllegalArgumentException를 던진다.
        );

        // Optional: 단 건 조회를 할 때 쓰인다.
        Optional<Like> checkUserAndComment = likeRepository.findByUserAndComment(user, comment);
        // 좋아요를 누른 user, comment를 찾는다.

        if(!checkUserAndComment.isPresent()){
            return new LikeResponseDto("해당 댓글의 좋아요가 존재하지 않습니다.", 400);
        } else {
            likeRepository.save(new Like(user, comment));
            comment.downLike(); //
            return new LikeResponseDto("해당 댓글의 좋아요가 취소되었습니다.", 200);
        }
    }
}
