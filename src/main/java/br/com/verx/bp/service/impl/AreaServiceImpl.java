package br.com.verx.bp.service.impl;

import static br.com.verx.bp.util.BpUtil.isNullOrEmpty;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.verx.bp.model.Area;
import br.com.verx.bp.repository.AreaRepository;
import br.com.verx.bp.service.AreaService;
import br.com.verx.bp.service.exception.AreaException;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AreaServiceImpl implements AreaService {
	
	@Autowired
	AreaRepository areaRepository;

	public AreaServiceImpl(AreaRepository areaRepository) {
		this.areaRepository = areaRepository;
	}

	private void validateArea(Area area) throws AreaException {
		if (isNullOrEmpty(area)) {
			throw new AreaException("Parâmetro AREA não foi preenchido.");
		}
		validateName(area.getName());
	}

	private void validateId(Long id) throws AreaException {
		if (isNullOrEmpty(id)) {
			throw new AreaException("Parâmetro ID não foi preenchido.");
		}
	}

	private void validateName(String name) throws AreaException {
		if (isNullOrEmpty(name)) {
			throw new AreaException("Parâmetro NOME não foi preenchido.");
		}
	}

	@Override
	public List<Area> findAll() throws AreaException {
		try {
			return areaRepository.findAll();
		} catch (Exception e) {
			throw new AreaException("Não foi possível buscar todas as areas. " + e.getMessage());
		}
	}

	@Override
	public List<Area> findAllByName(String name) throws AreaException {
		validateName(name);
		try {
			return areaRepository.findByName(name);
		} catch (Exception e) {
			throw new AreaException("Não foi possível buscar areas pelo nome. " + e.getMessage());
		}
	}

	@Override
	public Area findOne(Long id) throws AreaException {
		validateId(id);
		try {
			Optional<Area> areaOpt = areaRepository.findById(id);
			return areaOpt.isPresent() ? areaOpt.get() : null;
		} catch (Exception e) {
			throw new AreaException("Não foi possível buscar areas pelo id. " + e.getMessage());
		}
	}

	@Override
	public Area save(Area area) throws AreaException {
		try {
			validateArea(area);
			return areaRepository.save(area);
		} catch (Exception e) {
			throw new AreaException("Não foi possível salvar. " + e.getMessage());
		}
	}


	private void updateOldFields(Area areaNew, Area areaOld) {
		areaOld.setName(areaNew.getName());
	}

	@Override
	public Area update(Long id, Area areaNew) throws AreaException {
		try {
			validateId(id);
			validateArea(areaNew);
			Optional<Area> areaOpt = areaRepository.findById(id);
			if (areaOpt.isPresent())
				return null;
			updateOldFields(areaNew, areaOpt.get());
			return areaOpt.get();
		} catch (Exception e) {
			throw new AreaException("Não foi possível atualizar. " + e.getMessage());
		}
	}

	@Override
	public boolean delete(Long id) throws AreaException {
		try {
			validateId(id);
			Optional<Area> areaOpt = areaRepository.findById(id);
			if (!areaOpt.isPresent())
				return false;
			areaRepository.delete(areaOpt.get());
			return true;
		} catch (Exception e) {
			throw new AreaException("Não foi possível remover. " + e.getMessage());
		}
	}

}