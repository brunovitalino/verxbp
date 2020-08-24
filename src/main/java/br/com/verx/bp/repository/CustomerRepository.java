package br.com.verx.bp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.verx.bp.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	List<Customer> findByName(String name);
	
	Optional<Customer> findByCpf(Long cpf);

}
