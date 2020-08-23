package br.com.verx.bp.service;

import static br.com.verx.bp.util.BpUtil.isNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.verx.bp.model.Area;
import br.com.verx.bp.repository.AreaRepository;
import br.com.verx.bp.service.exception.AreaException;
import br.com.verx.bp.service.impl.AreaServiceImpl;;

public class AreaServiceTest {
	
	private Area area;
	private AreaRepository areaRepository;
	private AreaService areaService;
	
	AreaServiceTest() {
		areaRepository = Mockito.mock(AreaRepository.class);
		areaService = new AreaServiceImpl(areaRepository);
	}

	@BeforeEach
	void beforeEach() {
		area = new Area("Test");
	}

	@AfterEach
	void resetMokito() {
		Mockito.reset(areaRepository);
	}
	
	@Test
	void testSuccessFindAll() {
		List<Area> objectToReturn = new ArrayList<>();
		objectToReturn.add(area);
		when(areaRepository.findAll()).thenReturn(objectToReturn);
		List<Area> objectReturned = null;
		try {
			objectReturned = areaService.findAll();
		} catch (AreaException e) {
			fail("AreaException should not occur.");
		}
		assertFalse(isNull(objectReturned));
		assertTrue(objectReturned.size() > 0);
	}

	@Test
	void testSuccessFindAllByName() {
		List<Area> objectToReturn = new ArrayList<>();
		objectToReturn.add(area);
		when(areaRepository.findByName("Test")).thenReturn(objectToReturn);
		List<Area> objectReturned = null;
		try {
			objectReturned = areaService.findAllByName("Test");
		} catch (AreaException e) {
			fail("AreaException should not occur.");
		}
		assertFalse(isNull(objectReturned));
		assertTrue(objectReturned.size() > 0);
	}

	@Test
	void testSuccessFindOne() {
		Long idBuscado = 1L;
		area.setId(idBuscado);
		Optional<Area> objectToReturn = Optional.ofNullable(area);
		when(areaRepository.findById(idBuscado)).thenReturn(objectToReturn);
		Area objectReturned = null;
		try {
			objectReturned = areaService.findOne(idBuscado);
		} catch (AreaException e) {
			fail("AreaException should not occur.");
		}
		assertFalse(isNull(objectReturned));
	}

	@Test
	void testSave() {
		Area objectToReturn = area;
		when(areaRepository.save(area)).thenReturn(objectToReturn);
		Area objectReturned = null;
		try {
			objectReturned = areaService.save(area);
		} catch (AreaException e) {
			fail("AreaException should not occur.");
		}
		assertFalse(isNull(objectReturned));
	}

}
