package com.cos.webflux.domain;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Flux;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, Long>{
	
	@Query("SELECT * FROM customer WHERE last_name = :lastName")
	Flux<Customer> findByLastName(String lastName);

//	@Modifying
//	@Query("UPDATE customer SET last_name = :lastName, first_name = :firstName WHERE id = :id")
//	Mono<Customer> mUpdate(String lastName, String firstName, Long id);
	
}
