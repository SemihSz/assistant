package com.spring.assistant.assistant.health.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author semih on Mart, 2020, 15.03.2020, 17:16:50
 */
@Getter
@Setter
public class SpecificCountryDataModel extends Information {

	private Date mostDeathDate;

	private Date mostRecoveryDate;

	private Date mostConfirmedDate;


	public SpecificCountryDataModel(Date date, long confirmed, long deaths, long recovered, Date mostDeathDate, Date mostRecoveryDate, Date mostConfirmedDate) {
		super(date, confirmed, deaths, recovered);
		this.mostDeathDate = mostDeathDate;
		this.mostRecoveryDate = mostRecoveryDate;
		this.mostConfirmedDate = mostConfirmedDate;
	}
}
