package com.ita.repositories;

import com.ita.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by asv on 01.04.17.
 */
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findFirst5ByAccountIdOrderByIdDesc(Long accountId);
}
