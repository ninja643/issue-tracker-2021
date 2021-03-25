package rs.ac.ni.pmf.web.issuetracker.rest;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import rs.ac.ni.pmf.web.issuetracker.model.dto.ProjectDTO;

@RequestMapping("/projects")
public interface ProjectsRestController
{
	@GetMapping("")
	List<ProjectDTO> getProjects();

	@GetMapping("/{id}")
	ProjectDTO getProjectById(@PathVariable final Long id);

	@PostMapping("")
	ProjectDTO addProject(@RequestBody final ProjectDTO project);
}
