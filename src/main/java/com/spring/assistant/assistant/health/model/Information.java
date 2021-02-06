package com.spring.assistant.assistant.health.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @author semih on Mart, 2020, 15.03.2020, 12:25:47
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Information {

	private Date date;
	private long confirmed;
	private long deaths;
	private long recovered;

	@JsonProperty("date")
	public Date getDate() {
		return date;
	}

	@JsonProperty("date")
	public void setDate(Date value) {
		this.date = value;
	}

	@JsonProperty("confirmed")
	public long getConfirmed() {
		return confirmed;
	}

	@JsonProperty("confirmed")
	public void setConfirmed(long value) {
		this.confirmed = value;
	}

	@JsonProperty("deaths")
	public long getDeaths() {
		return deaths;
	}

	@JsonProperty("deaths")
	public void setDeaths(long value) {
		this.deaths = value;
	}

	@JsonProperty("recovered")
	public long getRecovered() {
		return recovered;
	}

	@JsonProperty("recovered")
	public void setRecovered(long value) {
		this.recovered = value;
	}
}
