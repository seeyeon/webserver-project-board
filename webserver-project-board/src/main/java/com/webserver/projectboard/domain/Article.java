package com.webserver.projectboard.domain;

import java.time.LocalDateTime;

public class Article {

    private Long id;
    private String title; //게시글 제목
    private String content; //게시글 본문(내용)
    private String hashtag; //게시글 해시태그

    private LocalDateTime createdAt; //생성일시
    private String createdBy; //생성자
    private LocalDateTime modifiedAt; //수정일시
    private String modifiedBy; //수정자

}

