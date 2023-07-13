package com.example.springblog.dto;

import lombok.*;

@Getter
@NoArgsConstructor // 생성자를 자동으로 생성
@ToString // 필드 값을 String으로 변환 , toString() 메서드 자동 생성

public class LikeResponseDto {
    private String msg; // Client에게 보내는 메시지
    private int code; // 상태코드 반환

    @Builder
    public LikeResponseDto(String msg, int code){
        this.msg = msg;
        this.code = code;
    }
}
