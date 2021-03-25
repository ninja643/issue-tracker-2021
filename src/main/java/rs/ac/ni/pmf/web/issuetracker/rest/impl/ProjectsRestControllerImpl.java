package rs.ac.ni.pmf.web.issuetracker.rest.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.issuetracker.exception.ProjectNotFoundException;
import rs.ac.ni.pmf.web.issuetracker.model.dto.ProjectDTO;
import rs.ac.ni.pmf.web.issuetracker.model.entity.ProjectEntity;
import rs.ac.ni.pmf.web.issuetracker.model.mapper.ProjectMapper;
import rs.ac.ni.pmf.web.issuetracker.repository.ProjectsRepository;
import rs.ac.ni.pmf.web.issuetracker.rest.ProjectsRestController;

@RestController
@RequiredArgsConstructor
public class ProjectsRestControllerImpl implements ProjectsRestController
{
	private final ProjectsRepository _projectsRepository;
	private final ProjectMapper _projectMapper;

	@Override
	public List<ProjectDTO> getProjects()
	{
		return _projectsRepository.findAll().stream()
			.map(_projectMapper::toDto)
			.collect(Collectors.toList());
	}

	@Override
	public ProjectDTO getProjectById(Long id)
	{
		return _projectsRepository.findById(id)
			.map(_projectMapper::toDto)
			.orElseThrow(() -> new ProjectNotFoundException("Project with id '" + id + "' does not exist"));
	}

	@Override
	public ProjectDTO addProject(ProjectDTO project)
	{
		final ProjectEntity projectEntity = _projectMapper.toEntity(project);
		final ProjectEntity savedEntity = _projectsRepository.save(projectEntity);
		return _projectMapper.toDto(savedEntity);
	}
}
