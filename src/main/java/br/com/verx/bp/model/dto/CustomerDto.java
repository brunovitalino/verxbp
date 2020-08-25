package br.com.verx.bp.model.dto;

import static br.com.verx.bp.util.BpUtil.isNullOrEmpty;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.verx.bp.model.Customer;;

public class CustomerDto {

	private Long id;
	
	@NotBlank//(message = "Name cannot be null or empty")
	private String name;
	
	@NotBlank//(message = "must be 11 digits")
	@Size(min = 11, max = 11)
	private String cpf;
	
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public static List<CustomerDto> toList(List<Customer> customers) {
		return customers.stream().map(CustomerDto::new).collect(Collectors.toList());
	}

	public Customer toEntity() {
		return new Customer(this.getId(), this.getName(), this.getCpf());
	}

}
