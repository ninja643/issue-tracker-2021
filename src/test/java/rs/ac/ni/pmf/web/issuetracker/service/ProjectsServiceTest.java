package rs.ac.ni.pmf.web.issuetracker.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import rs.ac.ni.pmf.web.issuetracker.exception.ProjectNotFoundException;
import rs.ac.ni.pmf.web.issuetracker.model.entity.ProjectEntity;

@DataJpaTest
@Import(ProjectsService.class)
class ProjectsServiceTest
{
	@Autowired
	private ProjectsService _projectsService;

	@BeforeEach
	public void initializeData()
	{
		_projectsService.addProject("Test project");
		_projectsService.addProject("Test project 2");
		_projectsService.addProject("Test project 3");
	}

	@Test
	public void shouldFindProject()
	{
		final ProjectEntity project = _projectsService.findByProjectName("Test project");

		assertThat(project.getName()).isEqualTo("Test project");
	}

	@Test
	public void shouldFailToFindProject()
	{
		assertThatThrownBy(() -> _projectsService.findByProjectName("Unknown"))
			.isInstanceOf(ProjectNotFoundException.class)
			.hasMessage("Project 'Unknown' does not exist");
	}
}