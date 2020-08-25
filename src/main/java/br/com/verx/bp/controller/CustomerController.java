package br.com.verx.bp.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.verx.bp.model.Customer;
import br.com.verx.bp.model.dto.CustomerDto;
import br.com.verx.bp.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping
	public ResponseEntity<List<CustomerDto>> findAll(@RequestParam(required = false) String name,
			@PageableDefault(sort = "name") Pageable pageable) {
		List<Customer> customers = null;
		if (name != null) {
			customers = customerService.findAllByName(name);
			return ResponseEntity.ok(CustomerDto.toList(customers));
		}
		customers = customerService.findAll();
		return ResponseEntity.ok(CustomerDto.toList(customers));
	}

	@GetMapping("/{id}")
	public ResponseEntity<CustomerDto> findOneById(@PathVariable Long id) {
		Optional<Customer> customer;
		customer = customerService.findOneById(id);
		return !customer.isPresent() ? ResponseEntity.notFound().build() : ResponseEntity.ok(new CustomerDto(customer.get()));
	}

	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<CustomerDto> findOneByCpf(@PathVariable String cpf) {
		Optional<Customer> customer;
		customer = customerService.findOneByCpf(cpf);
		return !customer.isPresent() ? ResponseEntity.notFound().build() : ResponseEntity.ok(new CustomerDto(customer.get()));
	}

	@PostMapping
	@Transactional
	public ResponseEntity<?> save(@RequestBody @Valid CustomerDto customerDto, UriComponentsBuilder uriBuilder) {
		Customer customer = customerDto.toEntity();
		customerService.save(customer);
		URI uri = uriBuilder.path("/customers/{id}").buildAndExpand(customer.getId()).toUri();
		return ResponseEntity.created(uri).body(new CustomerDto(customer));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<CustomerDto> update(@PathVariable Long id, @RequestBody CustomerDto customerDto) {
		Optional<Customer> customerOpt = customerService.findOneById(id);
		if (!customerOpt.isPresent())
			return ResponseEntity.notFound().build();
		customerOpt = Optional.ofNullable(customerService.update(id, customerDto.toEntity()));
		return ResponseEntity.ok(new CustomerDto(customerOpt.get()));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id) {
		if (!customerService.delete(id))
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok().build();
	}

}
