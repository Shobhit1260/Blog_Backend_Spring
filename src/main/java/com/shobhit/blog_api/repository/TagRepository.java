package com.shobhit.blog_api.repository;

import com.shobhit.blog_api.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag,Long> {
}
