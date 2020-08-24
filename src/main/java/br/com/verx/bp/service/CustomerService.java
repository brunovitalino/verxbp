package br.com.verx.bp.service;

import java.util.List;
import java.util.Optional;

import br.com.verx.bp.model.Customer;
import br.com.verx.bp.service.exception.CustomerException;

public interface CustomerService {
	
	/**
	 * Method to find all customers.
	 */
	List<Customer> findAll() throws CustomerException;
	
	/**
	 * Method to find all customers by name.
	 */
	List<Customer> findAllByName(String name) throws CustomerException;
	
	/**
	 * Method to find one customer by primary key.
	 */
	Optional<Customer> findOneById(Long id) throws CustomerException;
	
	/**
	 * Method to find one customer by name.
	 */
	Optional<Customer> findOneByCpf(Long cpf) throws CustomerException;
	
	/**
	 * Method para salvar one customer.
	 */
	Customer save(Customer customer) throws CustomerException;
	
	/**
	 * Method para atualizar one customer.
	 */
	Customer update(Long id, Customer customerNew) throws CustomerException;
	
	/**
	 * Method para remover one customer.
	 */
	boolean delete(Long id) throws CustomerException;

}
