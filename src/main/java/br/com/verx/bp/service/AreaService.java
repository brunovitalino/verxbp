package br.com.verx.bp.service;

import java.util.List;

import br.com.verx.bp.model.Area;

public interface AreaService {
	
	/**
	 * Method to find all areas.
	 */
	List<Area> findAll();
	
	/**
	 * Method to find one area by name.
	 */
	List<Area> findAllByName(String name);
	
	/**
	 * Method to find one area by primary key.
	 */
	Area findOne(Long id);
	
	/**
	 * Method to save one area.
	 */
	Area save(Area area);
	
	/**
	 * Method to update one area.
	 */
	Area update(Long id, Area areaNew);
	
	/**
	 * Method to remove one area.
	 */
	boolean delete(Long id);

}
