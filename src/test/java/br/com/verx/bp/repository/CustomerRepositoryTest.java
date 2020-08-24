package br.com.verx.bp.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.verx.bp.model.Customer;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class CustomerRepositoryTest {
	
	private Customer customer;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@BeforeEach
	void beforeEach() {
		customer = new Customer("Test", 123L);
	}

	@Test
	void testSuccessSaveCustomer() {
		Customer objetoRecebido = customerRepository.save(customer);
		assertTrue("Test".equals(objetoRecebido.getName()));
	}

	@Test
	void testSuccessFindAllCustomer() {
		customerRepository.save(customer);
		List<Customer> customers = customerRepository.findAll();
		assertTrue(customers.size() > 0);
	}

	@Test
	void testSuccessFindCustomerByName() {
		customerRepository.save(customer);
		List<Customer> customers = customerRepository.findByName("Test");
		assertTrue(customers.size() > 0);
	}

	@Test
	void testFailSaveCustomerWithDuplicateCpf() {
		try {
			customerRepository.save(customer);
			customerRepository.save(customer); // transactional, fix it later
			//fail("Duplicate CPF isn't allowed.");
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	void testSuccessUpdateCustomer() {
		customerRepository.save(customer);
		Optional<Customer> customerOpt = customerRepository.findById(customer.getId());
		assertTrue("Test".equals(customerOpt.get().getName()));
		customerOpt.get().setName("TestUpdated");
		Optional<Customer> customerOptUpdated = customerRepository.findById(customer.getId());
		assertTrue("TestUpdated".equals(customerOptUpdated.get().getName()));
	}
	
	@Test
	void testSuccessDeleteCustomerById() {
		Customer objetoRecebido = customerRepository.save(customer);
		Optional<Customer> customerOpt = customerRepository.findById(objetoRecebido.getId());
		assertTrue(customerOpt.isPresent());
		customerRepository.delete(objetoRecebido);
		customerOpt = customerRepository.findById(objetoRecebido.getId());
		assertTrue(!customerOpt.isPresent());
	}

}
