package rs.ac.ni.pmf.web.issuetracker.model.mapper;

import org.springframework.stereotype.Component;
import rs.ac.ni.pmf.web.issuetracker.model.dto.ProjectDTO;
import rs.ac.ni.pmf.web.issuetracker.model.entity.ProjectEntity;

@Component
public class ProjectMapper
{
	public ProjectDTO toDto(final ProjectEntity entity)
	{
		return ProjectDTO.builder()
			.id(entity.getId())
			.name(entity.getName())
			.startDate(entity.getStartDate())
			.modifiedOn(entity.getModifiedOn())
			.build();
	}

	public ProjectEntity toEntity(final ProjectDTO dto)
	{
		return ProjectEntity.builder()
			.id(dto.getId())
			.name(dto.getName())
			.startDate(dto.getStartDate())
			.build();
	}
}
