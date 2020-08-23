package br.com.verx.bp.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.verx.bp.model.Area;
import br.com.verx.bp.model.dto.AreaDto;
import br.com.verx.bp.service.AreaService;
import br.com.verx.bp.service.exception.AreaException;

@RestController
@RequestMapping("/areas")
public class AreaController {

	@Autowired
	private AreaService areaService;

	@GetMapping
	public ResponseEntity<List<AreaDto>> findAll(@RequestParam(required = false) String name,
			@PageableDefault(sort = "nome", direction = Direction.ASC) Pageable pageable) {
		try {
			List<Area> areas = null;
			if (name != null) {
				areas = areaService.findAllByName(name);
				return ResponseEntity.ok(AreaDto.toList(areas));
			}
			areas = areaService.findAll();
			return ResponseEntity.ok(AreaDto.toList(areas));
		} catch (AreaException e) {
			e.printStackTrace();
			return ResponseEntity.unprocessableEntity().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<AreaDto> findOne(@PathVariable Long id) {
		Area area;
		try {
			area = areaService.findOne(id);
			return area == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(new AreaDto(area));
		} catch (AreaException e) {
			e.printStackTrace();
			return ResponseEntity.unprocessableEntity().build();
		}
	}

	@PostMapping
	@Transactional
	public ResponseEntity<AreaDto> save(@RequestBody @Valid AreaDto areaDto, UriComponentsBuilder uriBuilder) {
		try {
			Area area = areaDto.toEntity();
			areaService.save(area);
			URI uri = uriBuilder.path("/areas/{id}").buildAndExpand(area.getId()).toUri();
			return ResponseEntity.created(uri).body(new AreaDto(area));
		} catch (AreaException e) {
			e.printStackTrace();
			return ResponseEntity.unprocessableEntity().build();
		}
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<AreaDto> update(@PathVariable Long id, @RequestBody @Valid AreaDto areaDto) {
		try {
			Area area = areaService.findOne(id);
			if (area == null)
				return ResponseEntity.notFound().build();
			area = areaService.update(id, areaDto.toEntity());
			return ResponseEntity.ok(new AreaDto(area));
		} catch (AreaException e) {
			e.printStackTrace();
			return ResponseEntity.unprocessableEntity().build();
		}
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			if (!areaService.delete(id))
				return ResponseEntity.notFound().build();
			return ResponseEntity.ok().build();
		} catch (AreaException e) {
			e.printStackTrace();
			return ResponseEntity.unprocessableEntity().build();
		}
	}

}
