package com.shobhit.blog_api.repository;

import com.shobhit.blog_api.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findByPost_Id(Long postId);
}
