package br.com.verx.bp.model.dto;

import static br.com.verx.bp.util.BpUtil.isNullOrEmpty;

import java.util.List;
import java.util.stream.Collectors;

import br.com.verx.bp.model.Customer;;

public class CustomerDto {

	private Long id;
	private String name;
	private Long cpf;
	
	public CustomerDto() {
	}

	public CustomerDto(Customer customer) {
		if (!isNullOrEmpty(customer)) {
			this.id = customer.getId();
			this.name = customer.getName();
			this.cpf = customer.getCpf();
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public static List<CustomerDto> toList(List<Customer> customers) {
		return customers.stream().map(CustomerDto::new).collect(Collectors.toList());
	}

	public Customer toEntity() {
		return new Customer(this.getId(), this.getName(), this.getCpf());
	}

}
