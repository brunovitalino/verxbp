package br.com.verx.bp.service;

import static br.com.verx.bp.util.BpUtil.isNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.verx.bp.model.Customer;
import br.com.verx.bp.repository.CustomerRepository;
import br.com.verx.bp.service.exception.CustomerException;
import br.com.verx.bp.service.impl.CustomerServiceImpl;;

public class CustomerServiceTest {
	
	private Customer customer;
	private CustomerRepository customerRepository;
	private CustomerService customerService;
	
	CustomerServiceTest() {
		customerRepository = Mockito.mock(CustomerRepository.class);
		customerService = new CustomerServiceImpl(customerRepository);
	}

	@BeforeEach
	void beforeEach() {
		customer = new Customer("Test", 123L);
	}

	@AfterEach
	void resetMokito() {
		Mockito.reset(customerRepository);
	}
	
	@Test
	void testSuccessFindAll() {
		List<Customer> objectToReturn = new ArrayList<>();
		objectToReturn.add(customer);
		when(customerRepository.findAll()).thenReturn(objectToReturn);
		List<Customer> objectReturned = null;
		try {
			objectReturned = customerService.findAll();
		} catch (CustomerException e) {
			fail("CustomerException should not occur.");
		}
		assertFalse(isNull(objectReturned));
		assertTrue(objectReturned.size() > 0);
	}

	@Test
	void testSuccessFindAllByName() {
		List<Customer> objectToReturn = new ArrayList<>();
		objectToReturn.add(customer);
		when(customerRepository.findByName("Test")).thenReturn(objectToReturn);
		List<Customer> objectReturned = null;
		try {
			objectReturned = customerService.findAllByName("Test");
		} catch (CustomerException e) {
			fail("CustomerException should not occur.");
		}
		assertFalse(isNull(objectReturned));
		assertTrue(objectReturned.size() > 0);
	}

	@Test
	void testSuccessFindOneById() {
		Long idTarget = 1L;
		customer.setId(idTarget);
		Optional<Customer> objectToReturn = Optional.ofNullable(customer);
		when(customerRepository.findById(idTarget)).thenReturn(objectToReturn);
		Optional<Customer> objectReturned = null;
		try {
			objectReturned = customerService.findOneById(idTarget);
		} catch (CustomerException e) {
			fail("CustomerException should not occur.");
		}
		assertTrue(objectReturned.isPresent());
	}

	@Test
	void testSuccessFindOneByCpf() {
		Long cpfTarget = 321L;
		customer.setCpf(cpfTarget);
		Optional<Customer> objectToReturn = Optional.ofNullable(customer);
		when(customerRepository.findByCpf(cpfTarget)).thenReturn(objectToReturn);
		Optional<Customer> objectReturned = null;
		try {
			objectReturned = customerService.findOneByCpf(cpfTarget);
		} catch (CustomerException e) {
			fail("CustomerException should not occur.");
		}
		assertTrue(objectReturned.isPresent());
	}

	@Test
	void testSave() {
		Customer objectToReturn = customer;
		when(customerRepository.save(customer)).thenReturn(objectToReturn);
		Customer objectReturned = null;
		try {
			objectReturned = customerService.save(customer);
		} catch (CustomerException e) {
			fail("CustomerException should not occur.");
		}
		assertFalse(isNull(objectReturned));
	}

}
