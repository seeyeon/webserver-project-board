package com.webserver.projectboard.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@DisplayName("View Controller - articles")
@WebMvcTest(ArticleController.class)
class ArticleControllerTest {
    private final MockMvc mvc;

    public ArticleControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[view] [GET] 게시글 리스트 (게시판) 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingArticlesView_thenReturnsArticlesView() throws Exception {
        //given

        //when&then
        mvc.perform(get("/articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_HTML))  //content 타입 확인
                .andExpect(model().attributeExists("articles")); //데이터가 있나없나 확인가능

    }

    @DisplayName("[view] [GET] 게시글 상세 페이지 - 정상 호출")
        @Test
        public void givenNothing_whenRequestingArticleView_thenReturnsArticleView() throws Exception {
        //given

        //when&then
        mvc.perform(get("/articles/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_HTML))  //content 타입 확인
                .andExpect(model().attributeExists("article")); //데이터가 있나없나 확인가능

    }

    @DisplayName("[view] [GET] 게시글 검색 전용 페이지 - 정상 호출")
            @Test
            public void givenNothing_whenRequestingArticleSearchView_thenReturnsArticleSearchView() throws Exception {
                //given

                //when&then
                mvc.perform(get("/articles/search"))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.TEXT_HTML));  //content 타입 확인
            }

    @DisplayName("[view] [GET] 게시글 해시태그 검색 페이지 - 정상 호출")
                @Test
                public void givenNothing_whenRequestingArticleHashtagSearchView_thenReturnsArticleHashtagSearchView() throws Exception {
                    //given

                    //when&then
                    mvc.perform(get("/articles/search-hashtag"))
                            .andExpect(status().isOk())
                            .andExpect(content().contentType(MediaType.TEXT_HTML));  //content 타입 확인
                }

}

