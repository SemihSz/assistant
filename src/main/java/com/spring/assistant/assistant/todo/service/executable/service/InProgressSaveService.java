package com.spring.assistant.assistant.todo.service.executable.service;

import com.spring.assistant.assistant.exception.BusinessException;
import com.spring.assistant.assistant.executable.interfaces.BasicTask;
import com.spring.assistant.assistant.executable.interfaces.service.GetUserIdService;
import com.spring.assistant.assistant.todo.entity.InProgressEntity;
import com.spring.assistant.assistant.todo.repository.InProgressRepository;
import com.spring.assistant.assistant.todo.service.executable.model.InProgressRequest;
import com.spring.assistant.assistant.todo.shared.enums.AvailableResponseType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author semih on Aralık, 2019, 19.12.2019, 22:59:20
 */
@Service
@AllArgsConstructor
public class InProgressSaveService implements BasicTask<InProgressRequest, InProgressEntity> {

	private final InProgressRepository inProgressRepository;

	private final GetUserIdService getUserIdService;

	private final ControlAvailableTaskService controlAvailableTaskService;

	//TODO ekranda göster kullanıcıya kaç progress hakının kaldığını

	@Override
	public InProgressEntity apply(InProgressRequest inProgressModel) {
		StringBuilder stringBuilder = new StringBuilder();
		boolean s = false;
		final AvailableResponseType availableResponseType = controlAvailableTaskService.apply(getUserIdService.getUserId());

		switch (availableResponseType) {

			case NONE:
				throw new BusinessException("Şu an alacağı maxiumum kapasitede iş bulunmaktadır.");

			case ONE:

				final InProgressEntity inProgressEntity = inProgressRepository.findByUserId(getUserIdService.getUserId());
				s = false;
				final InProgressEntity inProgressEntityOne = InProgressEntity.builder()
						.inProgressTaskOne(!inProgressEntity.getInProgressTaskOne().isEmpty() ?
								inProgressEntity.getInProgressTaskOne() : (!inProgressModel.getInProgressTaskOne().isEmpty()) ?
								inProgressModel.getInProgressTaskOne() : inProgressModel.getInProgressTaskTwo())
						.inProgressTaskTwo(inProgressEntity.getInProgressTaskTwo() != null ?
								inProgressEntity.getInProgressTaskTwo() : (!inProgressModel.getInProgressTaskTwo().isEmpty()) ?
								inProgressModel.getInProgressTaskTwo() : inProgressModel.getInProgressTaskOne())
						.available(s)
						.userId(getUserIdService.getUserId())
						.build();


				inProgressRepository.save(inProgressEntityOne);
				break;

			case TWO:
				s = true;
				final InProgressEntity inProgressEntityTwo = InProgressEntity.builder()
						.inProgressTaskOne(inProgressModel.getInProgressTaskOne())
						.inProgressTaskTwo(inProgressModel.getInProgressTaskTwo())
						.available(s)
						.userId(getUserIdService.getUserId())
						.build();


				inProgressRepository.save(inProgressEntityTwo);
				break;

		}
		return inProgressRepository.findByUserId(getUserIdService.getUserId());
	}
}
