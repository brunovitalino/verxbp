package br.com.verx.bp.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String name;

	@NotBlank
	private String cpf;

	@OneToMany(mappedBy = "customer")
	private List<Address> addresses;

	public Customer(@NotBlank String name, @NotBlank String cpf) {
		this.name = name;
		this.cpf = cpf;
	}

	public Customer(Long id, @NotBlank String name, @NotBlank String cpf) {
		this(name, cpf);
		this.id = id;
	}

	public Customer(@NotBlank String name, @NotBlank String cpf, List<Address> addresses) {
		this(name, cpf);
		this.addresses = addresses;
	}

	public Customer(Long id, @NotBlank String name, @NotBlank String cpf, List<Address> addresses) {
		this(id, name, cpf);
		this.addresses = addresses;
	}

}
