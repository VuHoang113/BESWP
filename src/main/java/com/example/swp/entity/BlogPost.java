package com.example.swp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "BlogPosts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postID;

    // Tham chiếu đến User, nếu bạn có entity User thì dùng @ManyToOne
    @ManyToOne
    @JoinColumn(name = "authorUserID", nullable = false)
    private User author;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "NVARCHAR(MAX)")
    private String content;

    @Column(nullable = false)
    private LocalDateTime publishDate = LocalDateTime.now();

    private LocalDateTime lastModifiedDate;

    private String category;

    @Column(nullable = false)
    private Integer views = 0;

    @Column(nullable = false)
    private String status = "draft";

    private String featuredImageURL;
}
