package br.com.verx.bp.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String name;

	@ManyToOne
	@NotNull
	private State state;

	@OneToMany(mappedBy = "city")
	private List<Address> addresses;

	public City(@NotBlank String name) {
		this.name = name;
	}

	public City(Long id, @NotBlank String name) {
		this(name);
		this.id = id;
	}

	public City(@NotBlank String name, List<Address> addresses) {
		this(name);
		this.addresses = addresses;
	}

	public City(Long id, @NotBlank String name, List<Address> addresses) {
		this(id, name);
		this.addresses = addresses;
	}

}
