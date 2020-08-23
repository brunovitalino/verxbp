package br.com.verx.bp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Address {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String line1;

	private String line2;
	
	private Area area;

	@NotNull
	private City city;
	
	@NotNull
	private State state;
	
	private Integer postalCode;

	public Address() {
	}

	public Address(@NotEmpty String line1, @NotNull City city, @NotNull State state) {
		this.line1 = line1;
		this.city = city;
		this.state = state;
	}

	public Address(Long id, @NotEmpty String line1, @NotNull City city, @NotNull State state) {
		this(line1, city, state);
		this.id = id;
	}

	public Address(@NotEmpty String line1, String line2, @NotNull City city, @NotNull State state, Integer postalCode) {
		this(line1, city, state);
		this.line2 = line2;
		this.postalCode = postalCode;
	}

	public Address(Long id, @NotEmpty String line1, String line2, @NotNull City city, @NotNull State state, Integer postalCode) {
		this(id, line1, city, state);
		this.line2 = line2;
		this.postalCode = postalCode;
	}

	public Address(@NotEmpty String line1, String line2, Area area, @NotNull City city, @NotNull State state,
			Integer postalCode) {
		this(line1, line2, city, state, postalCode);
		this.area = area;
	}

	public Address(Long id, @NotEmpty String line1, String line2, Area area, @NotNull City city, @NotNull State state,
			Integer postalCode) {
		this(id, line1, line2, city, state, postalCode);
		this.area = area;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Integer getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
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
		Address other = (Address) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
