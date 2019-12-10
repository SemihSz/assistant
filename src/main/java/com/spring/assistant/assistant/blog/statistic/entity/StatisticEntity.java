package com.spring.assistant.assistant.blog.statistic.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Map;


/**
 * @author semih on Aralık, 2019, 14.12.2019, 12:48:55
 */
@Entity
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table
@NoArgsConstructor
public class StatisticEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String userId;

	private Long bodyLength;


	private String mostCommonWords;

	@ElementCollection
	private Map<String, Integer> fewCommonWords;

	private String badgeResponsesList;

	private String category;

	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;


}
