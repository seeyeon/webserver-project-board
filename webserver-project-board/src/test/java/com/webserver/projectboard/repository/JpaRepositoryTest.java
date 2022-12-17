package com.webserver.projectboard.repository;

import com.webserver.projectboard.config.JpaConfig;
import com.webserver.projectboard.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("JPA connection Test")
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public JpaRepositoryTest(
            @Autowired ArticleRepository articleRepository,
            @Autowired ArticleCommentRepository articleCommentRepository)
    {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @DisplayName("delete Test")
       @Test
       void givenTestData_whenDeleting_thenWorksFine() {
           //Given
           Article article = articleRepository.findById(1L).orElseThrow();
           long previousArticleCount = articleRepository.count();
           long previousArticleCommentCount = articleCommentRepository.count();
           int deletedCommentsSize = article.getArticleComments().size();


           //When
           articleRepository.delete(article);

           //Then
           assertThat(articleRepository.count()).isEqualTo(previousArticleCount-1);
           assertThat(articleCommentRepository.count()).isEqualTo(previousArticleCommentCount-deletedCommentsSize);
    }

}

