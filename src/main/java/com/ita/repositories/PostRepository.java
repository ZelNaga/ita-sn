package com.ita.repositories;

import com.ita.entities.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by asv on 01.04.17.
 */
public interface PostRepository extends PagingAndSortingRepository<Post, Long> {

    List<Post> findAllByAccountIdOrderByIdDesc(Long accountId, Pageable pageable);
}
