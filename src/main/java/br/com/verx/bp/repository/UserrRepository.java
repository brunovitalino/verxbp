package br.com.verx.bp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.verx.bp.model.Userr;

@Repository
public interface UserrRepository extends JpaRepository<Userr, Long> {
	
	Optional<Userr> findByEmail(String email);

}
