package com.spring.assistant.assistant.pagination;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author semih on Aralık, 2019, 15.12.2019, 22:40:29
 */
@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

	Customer findByName(String name);
}
