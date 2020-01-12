package com.spring.assistant.assistant.blog.statistic.repository;

import com.spring.assistant.assistant.blog.statistic.entity.StatisticEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author semih on Aralık, 2019, 14.12.2019, 17:49:32
 */

@Repository
public interface StatisticRepository extends CrudRepository<StatisticEntity, Long> {
}
