package com.example.springblog.controller;

import com.example.springblog.dto.LikeResponseDto;
import com.example.springblog.security.UserDetailsImpl;
import com.example.springblog.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    // 게시글 Like Api
    @PostMapping("/post/like/{id}")
    public LikeResponseDto likePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return likeService.likePost(id, userDetails.getUser());
    }

    // 게시글 Like 취소 API
    @DeleteMapping("/post/like/{id}")
    public LikeResponseDto deleteLikePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return likeService.deleteLikePost(id, userDetails.getUser());
    }
}
