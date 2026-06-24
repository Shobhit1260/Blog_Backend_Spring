package com.shobhit.blog_api.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Entity
@Table(name="comments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String content;
   @ManyToOne
   @JoinColumn(name = "post_id", nullable = false)
   private Post post;
   @ManyToOne
   @JoinColumn(name = "user_id", nullable = false)
   private User author;
   private LocalDateTime createdAt;
   private LocalDateTime updatedAt;
}
