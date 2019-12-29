package com.spring.assistant.assistant.todo.shared.utils;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author semih on Aralık, 2019, 29.12.2019, 01:08:33
 */
@NoArgsConstructor
@Component
public class CalculateAverageUtil {

	/**
	 * s1 general, s2 user score
	 **/

	private static final double INITIAL_VALUE = 0.0;

	public double getBothScore(double s1, double s2) {

		return calculateScore(s1, s2);
	}

	private double calculateScore(double s1, double s2) {

		double finalResult = 0.0;

		if (s1 == INITIAL_VALUE && s2 == INITIAL_VALUE) {
			return 0.0;
		} else {

			if (s1 > s2) {

				finalResult = (s1 * 0.7 + s2 * 0.3);

				return finalResult;
			} else
				return finalResult = (s1 * 0.5 + s2 * 0.5);

		}

	}

}
