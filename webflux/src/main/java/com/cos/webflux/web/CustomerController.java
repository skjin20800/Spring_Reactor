package com.cos.webflux.web;

import java.time.Duration;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.webflux.domain.Customer;
import com.cos.webflux.domain.CustomerRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class CustomerController {
	
	private final CustomerRepository customerRepository;

	@PostMapping("/customer")
	public Mono<Customer> save(@RequestBody Customer customer){
		return customerRepository.save(customer);
	}
	
//	                                                              produces = MediaType.TEXT_EVENT_STREAM_VALUE : SSE프로토콜에선 이형식으로 데이터를 받음
	// 														  produces = MediaType.APPLICATION_STREAM_JSON_VALUE : JSON타입으로 받는법 ( 일반 데이터 받을 때)
	@GetMapping(value = "/customer")
	public Flux<Customer> findAll(){
		return customerRepository.findAll().delayElements(Duration.ofSeconds(1)).log();
	}
	
	@GetMapping("/customer/{id}")
	public Mono<Customer> findById(@PathVariable Long id){
		return customerRepository.findById(id);
	}
	
	@DeleteMapping("/customer/{id}")
	public Mono<Void> deleteById(@PathVariable Long id){
		return customerRepository.deleteById(id).log();
	}
	
	@PutMapping("/customer/{id}")
	public Mono<Customer> update(@PathVariable Long id, @RequestBody Customer customer){
		customer.setId(id);
		return customerRepository.save(customer);
	}
	
}
