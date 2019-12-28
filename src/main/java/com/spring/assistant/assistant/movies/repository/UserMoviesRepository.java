package com.spring.assistant.assistant.movies.repository;

import com.spring.assistant.assistant.movies.entity.UserMoviesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author semih on Aralık, 2019, 22.12.2019, 15:50:45
 */
@Repository
public interface UserMoviesRepository extends JpaRepository<UserMoviesEntity, Long> {

	List<UserMoviesEntity> findByUserId(String userId);
}
