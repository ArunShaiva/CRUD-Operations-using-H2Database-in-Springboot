package com.example.crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

import com.example.crud.entity.Customer;
import com.example.crud.repository.CustomerRepository;

import jakarta.websocket.server.PathParam;

@RestController
public class CustomerController {

	@Autowired
	CustomerRepository repo;

	@PostMapping("/customers")
	public ResponseEntity<Customer> save(@RequestBody Customer customer) {
		try {
			return new ResponseEntity<Customer>(repo.save(customer), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		List<Customer> list = repo.findAll();
		if (list.isEmpty()) {

			return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Customer>>(list, HttpStatus.OK);

	}

	@GetMapping("/customers/{id}")
	public ResponseEntity<Customer> getSingleCustomer(@PathVariable int id) {
		Optional<Customer> customer = repo.findById(id);

		if (customer.isPresent()) {
			return new ResponseEntity<Customer>(customer.get(), HttpStatus.OK);
		}
		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);

	}

	@PutMapping("customers/{id}")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
		try {
			return new ResponseEntity<Customer>(repo.save(customer), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Customer>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("customers/{id}")
	public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable int id) {
		try {
			Optional<Customer> customer = repo.findById(id);

			if (customer.isPresent()) {
				repo.delete(customer.get());
			}
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);

		} catch (Exception e) {
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
