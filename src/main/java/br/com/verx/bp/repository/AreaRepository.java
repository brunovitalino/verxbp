package br.com.verx.bp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.verx.bp.model.Area;

public interface AreaRepository extends JpaRepository<Area, Long> {
	
	public List<Area> findByName(String name);

}
