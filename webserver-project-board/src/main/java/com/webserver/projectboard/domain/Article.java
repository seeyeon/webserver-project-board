package com.webserver.projectboard.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})

@Entity
public class Article extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //JPA을 이용한 id 자동 부여

    @Setter @Column(nullable = false) private String title; //게시글 제목
    @Setter @Column(nullable = false , length = 10000) private String content; //게시글 본문(내용)
    @Setter private String hashtag; //게시글 해시태그

    @ToString.Exclude
    @OrderBy("id")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL) // article에 연동된 댓글은 중복 허용X 모아서 보기 위함
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();



    protected Article() { } //기본 생성자

    private Article(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    public static Article of(String title, String content, String hashtag) {
        return new Article(title, content, hashtag);
    }

    //id entity를 DB에 영속화 시키고 연결짓고 사용하는 환경에서 서로 다른 두 id가 같은 조건이 무엇인가?

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return id !=null && id.equals(article.id); //id가 영속화x(부여되지 않은 경우) 다 다른것으로 간주한다.
                                                   //id가 있다면, id로 동등성 검사를 한다.
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}