package br.com.verx.bp.service.impl;

import static br.com.verx.bp.util.BpUtil.isNull;
import static br.com.verx.bp.util.BpUtil.isNullOrEmpty;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
			throw new CustomerException("O CLIENTE deve ser preenchido.");
		}
	}

	private void validateId(Long id) throws CustomerException {
		if (isNull(id)) {
			throw new CustomerException("O ID deve ser preenchido.");
		}
	}

	private void validateCpf(String cpf) throws CustomerException {
		if (isNullOrEmpty(cpf)) {
			throw new CustomerException("O CPF deve ser preenchido.");
		}
	}

	private void validateName(String name) throws CustomerException {
		if (isNullOrEmpty(name)) {
			throw new CustomerException("O NOME deve ser preenchido.");
		}
	}

	@Override
	public List<Customer> findAll() {
		try {
			return customerRepository.findAll();
		} catch (Exception e) {
			throw new CustomerException("Não foi possível buscar todos clientes.", e);
		}
	}

	@Override
	public List<Customer> findAllByName(String name) {
		validateName(name);
		try {
			return customerRepository.findByName(name);
		} catch (Exception e) {
			throw new CustomerException("Não foi possível buscar clientes pelo nome.", e);
		}
	}

	@Override
	public Optional<Customer> findOneById(Long id) {
		validateId(id);
		try {
			return customerRepository.findById(id);
		} catch (Exception e) {
			throw new CustomerException("Não foi possível buscar cliente pelo id.", e);
		}
	}

	@Override
	public Optional<Customer> findOneByCpf(String cpf) {
		validateCpf(cpf);
		try {
			return customerRepository.findByCpf(cpf);
		} catch (Exception e) {
			throw new CustomerException("Não foi possível buscar cliente pelo cpf.", e);
		}
	}

	@Override
	public Customer save(Customer customer) {
		try {
			validateCustomer(customer);
			validateName(customer.getName());
			validateCpf(customer.getCpf());
			return customerRepository.save(customer);
		} catch (CustomerException e) {
			throw new CustomerException("Não foi possível salvar cliente.", e);
		} catch (DataIntegrityViolationException e) {
			throw new CustomerException(e);
		} catch (ConstraintViolationException e) {
			throw new CustomerException(e);
		} catch (Exception e) {
			throw new CustomerException(e);
		}
	}

	private void updateOldFields(Customer customerNew, Customer customerOld) {
		customerOld.setName(!isNullOrEmpty(customerNew.getName()) ? customerNew.getName() : customerOld.getName());
		customerOld.setCpf(!isNull(customerNew.getCpf()) ? customerNew.getCpf() : customerOld.getCpf());
	}

	@Override
	public Customer update(Long id, Customer customerNew) {
		try {
//			validateId(id);
			validateCustomer(customerNew);
			Optional<Customer> customerOpt = customerRepository.findById(id);
			if (!customerOpt.isPresent())
				return null;
			updateOldFields(customerNew, customerOpt.get());
			return customerOpt.get();
		} catch (CustomerException e) {
			throw new CustomerException("Não foi possível atualizar cliente.", e);
		} catch (DataIntegrityViolationException e) {
			throw new CustomerException(e);
		} catch (ConstraintViolationException e) {
			throw new CustomerException(e);
		} catch (Exception e) {
			throw new CustomerException(e);
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
		} catch (CustomerException e) {
			throw new CustomerException("Não foi possível remover cliente.", e);
		}
	}

}
