package com.spring.assistant.assistant.todo.service.executable.service;

import com.spring.assistant.assistant.executable.interfaces.BasicTask;
import com.spring.assistant.assistant.todo.entity.InProgressEntity;
import com.spring.assistant.assistant.todo.repository.InProgressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author semih on Aralık, 2019, 22.12.2019, 13:18:21
 */
@Service
@AllArgsConstructor
public class CreateAutoInProgressSaveService implements BasicTask<String, InProgressEntity> {


	private final InProgressRepository inProgressRepository;

	@Override
	public InProgressEntity apply(String userId) {

		final InProgressEntity inProgressEntity = InProgressEntity.builder()
				.available(true)
				.userId(userId)
				.build();

		return inProgressRepository.save(inProgressEntity);
	}
}
