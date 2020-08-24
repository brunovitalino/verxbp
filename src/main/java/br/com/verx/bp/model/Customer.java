package br.com.verx.bp.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

//@Data
//@NoArgsConstructor
@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String name;

	@NotNull @Column(unique = true)
	private Long cpf;

	@OneToMany(mappedBy = "customer")
	private List<Address> addresses;

	public Customer(@NotBlank String name, @NotNull Long cpf) {
		this.name = name;
		this.cpf = cpf;
	}

	public Customer(Long id, @NotBlank String name, @NotNull Long cpf) {
		this(name, cpf);
		this.id = id;
	}

	public Customer(@NotBlank String name, @NotNull Long cpf, List<Address> addresses) {
		this(name, cpf);
		this.addresses = addresses;
	}

	public Customer(Long id, @NotBlank String name, @NotNull Long cpf, List<Address> addresses) {
		this(id, name, cpf);
		this.addresses = addresses;
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

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
