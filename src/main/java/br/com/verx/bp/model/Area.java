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
public class Area {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String name;

	@OneToMany(mappedBy = "area")
	private List<Address> addresses;

	public Area(@NotBlank String name) {
		this.name = name;
	}

	public Area(Long id, @NotBlank String name) {
		this(name);
		this.id = id;
	}

	public Area(@NotBlank String name, List<Address> addresses) {
		this(name);
		this.addresses = addresses;
	}

	public Area(Long id, @NotBlank String name, List<Address> addresses) {
		this(id, name);
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

}
