package com.spring.assistant.assistant.todo.repository;

import com.spring.assistant.assistant.todo.entity.InProgressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author semih on Aralık, 2019, 19.12.2019, 22:59:48
 */
@Repository
public interface InProgressRepository extends JpaRepository<InProgressEntity, Long> {

	InProgressEntity findByUserId(String userId);

}
