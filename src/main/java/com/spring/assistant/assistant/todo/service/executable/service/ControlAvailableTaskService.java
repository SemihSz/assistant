package com.spring.assistant.assistant.todo.service.executable.service;

import com.spring.assistant.assistant.executable.interfaces.BasicTask;
import com.spring.assistant.assistant.todo.entity.InProgressEntity;
import com.spring.assistant.assistant.todo.repository.InProgressRepository;
import com.spring.assistant.assistant.todo.shared.enums.AvailableResponseType;
import com.spring.assistant.assistant.todo.shared.utils.IsEmptyControlUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author semih on Aralık, 2019, 19.12.2019, 23:06:38
 */

@Service
@AllArgsConstructor
public class ControlAvailableTaskService implements BasicTask<String, AvailableResponseType> {

	private final InProgressRepository inProgressRepository;

	private final IsEmptyControlUtil controlUtil;


	@Override
	public AvailableResponseType apply(String s) {


		final InProgressEntity inProgressEntity = inProgressRepository.findByUserId(s);


		if (controlUtil.isTwoInputIsNotEmpty(inProgressEntity.getInProgressTaskOne(), inProgressEntity.getInProgressTaskTwo())) {

			return AvailableResponseType.NONE;
			//throw new BusinessException("Şu an 2 tane task yapılmaktadır");

		} else if (controlUtil.oneOfThemEmpty(inProgressEntity.getInProgressTaskOne(), inProgressEntity.getInProgressTaskTwo())) {

			return AvailableResponseType.ONE;

		} else {

			return AvailableResponseType.TWO;

		}

	}
}
