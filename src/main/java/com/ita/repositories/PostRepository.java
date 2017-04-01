package com.ita.repositories;

import com.ita.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by asv on 01.04.17.
 */
public interface PostRepository extends JpaRepository<Post, Long> {

    Post findByAccountId(Long accountId);
}
