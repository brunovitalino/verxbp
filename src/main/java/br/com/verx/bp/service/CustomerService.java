package br.com.verx.bp.service;

import java.util.List;
import java.util.Optional;

import br.com.verx.bp.model.Customer;

public interface CustomerService {
	
	/**
	 * Method to find all customers.
	 */
	List<Customer> findAll();
	
	/**
	 * Method to find all customers by name.
	 */
	List<Customer> findAllByName(String name);
	
	/**
	 * Method to find one customer by primary key.
	 */
	Optional<Customer> findOneById(Long id);
	
	/**
	 * Method to find one customer by name.
	 */
	Optional<Customer> findOneByCpf(String cpf);
	
	/**
	 * Method to save one customer.
	 */
	Customer save(Customer customer);
	
	/**
	 * Method to update one customer.
	 */
	Customer update(Long id, Customer customerNew);
	
	/**
	 * Method to remove one customer.
	 */
	boolean delete(Long id);

}
