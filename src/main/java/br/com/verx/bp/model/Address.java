package br.com.verx.bp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@NotNull
	private Customer customer;

	@NotBlank
	private String line1;

	private String line2;

	@ManyToOne
	private Area area;

	@ManyToOne
	@NotNull
	private City city;

	private Integer postalCode;

	public Address(@NotNull Customer customer, @NotBlank String line1, @NotNull City city) {
		this.customer = customer;
		this.line1 = line1;
		this.city = city;
	}

	public Address(Long id, @NotNull Customer customer, @NotBlank String line1, @NotNull City city) {
		this(customer, line1, city);
		this.id = id;
	}

	public Address(@NotNull Customer customer, @NotBlank String line1, String line2, @NotNull City city,
			Integer postalCode) {
		this(customer, line1, city);
		this.line2 = line2;
		this.postalCode = postalCode;
	}

	public Address(Long id, @NotNull Customer customer, @NotBlank String line1, String line2, @NotNull City city,
			Integer postalCode) {
		this(id, customer, line1, city);
		this.line2 = line2;
		this.postalCode = postalCode;
	}

	public Address(@NotNull Customer customer, @NotBlank String line1, String line2, Area area, @NotNull City city,
			Integer postalCode) {
		this(customer, line1, line2, city, postalCode);
		this.area = area;
	}

	public Address(Long id, @NotNull Customer customer, @NotBlank String line1, String line2, Area area,
			@NotNull City city, Integer postalCode) {
		this(id, customer, line1, line2, city, postalCode);
		this.area = area;
	}

}
