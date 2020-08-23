package br.com.verx.bp.service;

import java.util.List;
import java.util.Optional;

import br.com.verx.bp.model.Area;
import br.com.verx.bp.service.exception.AreaException;

public interface AreaService {
	
	/**
	 * Method to find all areas.
	 */
	List<Area> findAll() throws AreaException;
	
	/**
	 * Method to find one area by name.
	 */
	List<Area> findAllByName(String name) throws AreaException;
	
	/**
	 * Method to find one area by primary key.
	 */
	Area findOne(Long id) throws AreaException;
	
	/**
	 * Method para salvar one area.
	 */
	Area save(Area area) throws AreaException;
	
	/**
	 * Method para atualizar one area.
	 */
	Area update(Long id, Area areaNew) throws AreaException;
	
	/**
	 * Method para remover one area.
	 */
	boolean delete(Long id) throws AreaException;

}
