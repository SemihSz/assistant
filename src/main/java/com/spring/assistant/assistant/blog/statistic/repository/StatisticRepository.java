package com.spring.assistant.assistant.blog.statistic.repository;

import com.spring.assistant.assistant.blog.statistic.entity.StatisticEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * @author semih on Aralık, 2019, 14.12.2019, 17:49:32
 */

@Repository
public interface StatisticRepository extends CrudRepository<StatisticEntity, Long> {

	@Query("SELECT SUM(t.bodyLength) FROM StatisticEntity t WHERE t.userId=:userId")
	Long getPostLength(@Param("userId") String userId);

	Collection<StatisticEntity> findByUserId(String userId);
}
