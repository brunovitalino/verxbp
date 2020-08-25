package br.com.verx.bp.service.impl;

import static br.com.verx.bp.util.BpUtil.isNullOrEmpty;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.verx.bp.model.Area;
import br.com.verx.bp.repository.AreaRepository;
import br.com.verx.bp.service.AreaService;
import br.com.verx.bp.service.exception.AreaException;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Service
public class AreaServiceImpl implements AreaService {
	
	@Autowired
	private AreaRepository areaRepository;

	public AreaServiceImpl(AreaRepository areaRepository) {
		this.areaRepository = areaRepository;
	}

	private void validateArea(Area area) throws AreaException {
		if (isNullOrEmpty(area)) {
			throw new AreaException("O AREA deve ser preenchido.");
		}
	}

	private void validateId(Long id) throws AreaException {
		if (isNullOrEmpty(id)) {
			throw new AreaException("O ID deve ser preenchido.");
		}
	}

	private void validateName(String name) throws AreaException {
		if (isNullOrEmpty(name)) {
			throw new AreaException("O NOME deve ser preenchido.");
		}
	}

	@Override
	public List<Area> findAll() {
		try {
			return areaRepository.findAll();
		} catch (Exception e) {
			throw new AreaException("Não foi possível buscar todos bairros.", e);
		}
	}

	@Override
	public List<Area> findAllByName(String name) {
		validateName(name);
		try {
			return areaRepository.findByName(name);
		} catch (Exception e) {
			throw new AreaException("Não foi possível buscar bairros pelo nome.", e);
		}
	}

	@Override
	public Area findOne(Long id) {
		validateId(id);
		try {
			Optional<Area> areaOpt = areaRepository.findById(id);
			return areaOpt.isPresent() ? areaOpt.get() : null;
		} catch (Exception e) {
			throw new AreaException("Não foi possível buscar bairros pelo id.", e);
		}
	}

	@Override
	public Area save(Area area) {
		try {
			validateArea(area);
			validateName(area.getName());
			return areaRepository.save(area);
		} catch (AreaException e) {
			throw new AreaException("Não foi possível salvar bairro.", e);
		} catch (DataIntegrityViolationException e) {
			throw new AreaException(e);
		} catch (ConstraintViolationException e) {
			throw new AreaException(e);
		} catch (Exception e) {
			throw new AreaException(e);
		}
	}


	private void updateOldFields(Area areaNew, Area areaOld) {
		areaOld.setName(!isNullOrEmpty(areaNew.getName()) ? areaNew.getName() : areaOld.getName());
	}

	@Override
	public Area update(Long id, Area areaNew) {
		try {
			validateArea(areaNew);
			Optional<Area> areaOpt = areaRepository.findById(id);
			if (!areaOpt.isPresent())
				return null;
			updateOldFields(areaNew, areaOpt.get());
			return areaOpt.get();
		} catch (AreaException e) {
			throw new AreaException("Não foi possível atualizar bairro.", e);
		} catch (DataIntegrityViolationException e) {
			throw new AreaException(e);
		} catch (ConstraintViolationException e) {
			throw new AreaException(e);
		} catch (Exception e) {
			throw new AreaException(e);
		}
	}

	@Override
	public boolean delete(Long id) {
		try {
			validateId(id);
			Optional<Area> areaOpt = areaRepository.findById(id);
			if (!areaOpt.isPresent())
				return false;
			areaRepository.delete(areaOpt.get());
			return true;
		} catch (Exception e) {
			throw new AreaException("Não foi possível remover bairro.", e);
		}
	}

}
