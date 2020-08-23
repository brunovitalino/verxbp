package br.com.verx.bp.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class State {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String name;
	
	@OneToMany(mappedBy = "state")
	private List<City> cities;
	
	public State() {
	}

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
		State other = (State) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
