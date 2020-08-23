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
public class State {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String name;

	@OneToMany(mappedBy = "state")
	private List<City> cities;

	public State(@NotBlank String name) {
		this.name = name;
	}

	public State(Long id, @NotBlank String name) {
		this(name);
		this.id = id;
	}

	public State(@NotBlank String name, List<City> cities) {
		this(name);
		this.cities = cities;
	}

	public State(Long id, @NotBlank String name, List<City> cities) {
		this(id, name);
		this.cities = cities;
	}

}
