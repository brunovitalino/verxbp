package br.com.verx.bp.service.impl;

import static br.com.verx.bp.util.BpUtil.isNullOrEmpty;
import static br.com.verx.bp.util.BpUtil.isNull;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.verx.bp.model.Customer;
import br.com.verx.bp.repository.CustomerRepository;
import br.com.verx.bp.service.CustomerService;
import br.com.verx.bp.service.exception.CustomerException;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	private void validateCustomer(Customer customer) throws CustomerException {
		if (isNullOrEmpty(customer)) {
			throw new CustomerException("Missing input param CUSTOMER.");
		}
		validateName(customer.getName());
	}

	private void validateId(Long id) throws CustomerException {
		if (isNull(id)) {
			throw new CustomerException("Missing input param ID.");
		}
	}

	private void validateCpf(Long cpf) throws CustomerException {
		if (isNull(cpf)) {
			throw new CustomerException("Missing input param CPF.");
		}
	}

	private void validateName(String name) throws CustomerException {
		if (isNullOrEmpty(name)) {
			throw new CustomerException("Missing input param NOME.");
		}
	}

	@Override
	public List<Customer> findAll() throws CustomerException {
		try {
			return customerRepository.findAll();
		} catch (Exception e) {
			throw new CustomerException("Não foi possível buscar todos clientes. " + e.getMessage());
		}
	}

	@Override
	public List<Customer> findAllByName(String name) throws CustomerException {
		validateName(name);
		try {
			return customerRepository.findByName(name);
		} catch (Exception e) {
			throw new CustomerException("Não foi possível buscar clientes pelo nome. " + e.getMessage());
		}
	}

	@Override
	public Optional<Customer> findOneById(Long id) throws CustomerException {
		validateId(id);
		try {
			return customerRepository.findById(id);
		} catch (Exception e) {
			throw new CustomerException("Não foi possível buscar cliente pelo id. " + e.getMessage());
		}
	}

	@Override
	public Optional<Customer> findOneByCpf(Long cpf) throws CustomerException {
		validateCpf(cpf);
		try {
			return customerRepository.findByCpf(cpf);
		} catch (Exception e) {
			throw new CustomerException("Não foi possível buscar cliente pelo cpf. " + e.getMessage());
		}
	}

	@Override
	public Customer save(Customer customer) throws CustomerException {
		try {
			validateCustomer(customer);
			return customerRepository.save(customer);
		} catch (Exception e) {
			throw new CustomerException("Não foi possível salvar cliente. " + e.getMessage());
		}
	}


	private void updateOldFields(Customer customerNew, Customer customerOld) {
		customerOld.setName(customerNew.getName());
	}

	@Override
	public Customer update(Long id, Customer customerNew) throws CustomerException {
		try {
			validateId(id);
			validateCustomer(customerNew);
			Optional<Customer> customerOpt = customerRepository.findById(id);
			if (!customerOpt.isPresent())
				return null;
			updateOldFields(customerNew, customerOpt.get());
			return customerOpt.get();
		} catch (Exception e) {
			throw new CustomerException("Não foi possível atualizar cliente. " + e.getMessage());
		}
	}

	@Override
	public boolean delete(Long id) throws CustomerException {
		try {
			validateId(id);
			Optional<Customer> customerOpt = customerRepository.findById(id);
			if (!customerOpt.isPresent())
				return false;
			customerRepository.delete(customerOpt.get());
			return true;
		} catch (Exception e) {
			throw new CustomerException("Não foi possível remover cliente. " + e.getMessage());
		}
	}

}
