package com.spring.assistant.assistant.blog.executable.service;


import com.spring.assistant.assistant.blog.model.PostBodyModel;
import com.spring.assistant.assistant.blog.statistic.BodyStatisticModel;
import com.spring.assistant.assistant.interfaces.SimpleTask;
import com.spring.assistant.assistant.todo.shared.utils.TextUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author semih on Aralık, 2019, 14.12.2019, 13:12:32
 */
@Service
@AllArgsConstructor
public class BodyStatisticService implements SimpleTask<PostBodyModel, BodyStatisticModel> {

	@Autowired
	TextUtil textUtil;

	@Override
	public BodyStatisticModel apply(PostBodyModel postBodyModel) {
		return createModel(postBodyModel);
	}

	public BodyStatisticModel createModel(PostBodyModel postBodyModel) {

		final String bodyMostCommonWords = textUtil.getMostCommonWords(postBodyModel);


		final BodyStatisticModel bodyStatisticModel = BodyStatisticModel.builder()
				.body(postBodyModel.getPostBody())
				.bodyLength((long) postBodyModel.getPostBody().length())
				.fetCommonWords(null)
				.mostCommonWords(bodyMostCommonWords)
				.build();

		return bodyStatisticModel;


	}
}
