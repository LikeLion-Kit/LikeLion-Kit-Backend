package com.example.demo.domain.post.domain;


import com.example.demo.domain.comment.domain.Comment;
import com.example.demo.domain.file.domain.entity.UploadFile;
import com.example.demo.domain.post_qna.domain.Post_Qna;
import com.example.demo.domain.user.domain.User;
import com.example.demo.domain.user.domain.vo.Track;
import com.example.demo.global.base.domain.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name ="post")
@NoArgsConstructor
@Getter
@Setter
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 45)
    @NotBlank(message = "제목은 빈 값일 수 없습니다.")
    private String title;

    @Column(nullable = false,length = 500)
    @NotBlank(message = "게시물은 빈 값일 수 없습니다.")
    private String contents;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Track track;

    @ManyToOne
    @JoinColumn(name ="user_id",nullable = false)
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post_Qna> postQnas;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UploadFile> uploadFiles;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UploadFile> files;


    @Builder
    public Post(String title, String contents, Track track, User user) {
        this.title = title;
        this.contents = contents;
        this.track = track;
        this.user = user;
    }
}
