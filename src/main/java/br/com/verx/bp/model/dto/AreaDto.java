package br.com.verx.bp.model.dto;

import static br.com.verx.bp.util.BpUtil.isNullOrEmpty;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotEmpty;

import br.com.verx.bp.model.Area;;

//@Data
//@NoArgsConstructor
public class AreaDto {

	private Long id;
	@NotEmpty
	private String name;
	
	public AreaDto() {
	}

	public AreaDto(Area area) {
		if (!isNullOrEmpty(area)) {
			this.id = area.getId();
			this.name = area.getName();
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static List<AreaDto> toList(List<Area> areas) {
		return areas.stream().map(AreaDto::new).collect(Collectors.toList());
	}

	public Area toEntity() {
		return new Area(this.getId(), this.getName());
	}

}
