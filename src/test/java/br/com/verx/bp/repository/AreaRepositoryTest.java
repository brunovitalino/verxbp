package br.com.verx.bp.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.verx.bp.model.Area;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class AreaRepositoryTest {
	
	private Area area;
	
	@Autowired
	AreaRepository areaRepository ;
	
	@BeforeEach
	void beforeEach() {
		area = new Area("Test");
	}

	@Test
	void testSuccessSaveArea() {
		Area objetoRecebido = areaRepository.save(area);
		assertTrue("Test".equals(objetoRecebido.getName()));
	}

	@Test
	void testSuccessFindAllArea() {
		areaRepository.save(area);
		List<Area> areas = areaRepository.findAll();
		assertTrue(areas.stream().findAny().isPresent());
	}

	@Test
	void testSuccessFindAreaByName() {
		areaRepository.save(area);
		List<Area> areas = areaRepository.findByName("Test");
		assertTrue(areas.size() > 0);
	}

	@Test
	void testSuccessUpdateArea() {
		areaRepository.save(area);
		Optional<Area> areaOpt = areaRepository.findById(area.getId());
		assertTrue("Test".equals(areaOpt.get().getName()));
		areaOpt.get().setName("TestUpdated");
		Optional<Area> areaOptUpdated = areaRepository.findById(area.getId());
		assertTrue("TestUpdated".equals(areaOptUpdated.get().getName()));
	}
	
	@Test
	void testSuccessDeleteAreaById() {
		Area objetoRecebido = areaRepository.save(area);
		Optional<Area> areaOpt = areaRepository.findById(objetoRecebido.getId());
		assertTrue(areaOpt.isPresent());
		areaRepository.delete(objetoRecebido);
		areaOpt = areaRepository.findById(objetoRecebido.getId());
		assertTrue(areaOpt.isEmpty());
	}

}
