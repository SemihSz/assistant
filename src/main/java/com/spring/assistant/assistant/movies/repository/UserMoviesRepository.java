package com.spring.assistant.assistant.movies.repository;

import com.spring.assistant.assistant.movies.entity.UserMoviesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author semih on Aralık, 2019, 22.12.2019, 15:50:45
 */
public interface UserMoviesRepository extends JpaRepository<UserMoviesEntity, Long> {
}
