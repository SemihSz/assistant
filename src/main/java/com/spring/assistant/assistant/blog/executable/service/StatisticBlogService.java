package com.spring.assistant.assistant.blog.executable.service;

import com.spring.assistant.assistant.blog.model.PostSaveModel;
import com.spring.assistant.assistant.blog.statistic.BadgeStatisticModel;
import com.spring.assistant.assistant.blog.statistic.BodyStatisticModel;
import com.spring.assistant.assistant.blog.statistic.entity.StatisticEntity;
import com.spring.assistant.assistant.blog.statistic.repository.StatisticRepository;
import com.spring.assistant.assistant.executable.interfaces.Mappers;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author semih on Aralık, 2019, 14.12.2019, 13:01:56
 */
@AllArgsConstructor
@Service
@Slf4j
public class StatisticBlogService implements Mappers<PostSaveModel, BadgeStatisticModel, BodyStatisticModel, StatisticEntity> {


	private final StatisticRepository statisticRepository;

	@Override
	public StatisticEntity apply(PostSaveModel postSaveModel, BadgeStatisticModel badgeStatisticModel, BodyStatisticModel bodyStatisticModel) {

		final StatisticEntity statisticEntity = StatisticEntity.builder()
				.userId(badgeStatisticModel.getUserId())
				.badgeResponsesList(badgeStatisticModel.getBadgeResponse())
				.bodyLength(bodyStatisticModel.getBodyLength())
				.category(postSaveModel.getCategory())
				.createdDate(postSaveModel.getCreateDate())
				.fewCommonWords(null)
				.mostCommonWords(bodyStatisticModel.getMostCommonWords())
				.updateDate(postSaveModel.getUpdatedDate())
				.build();

		return statisticRepository.save(statisticEntity);
	}
}
