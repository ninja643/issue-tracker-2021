package rs.ac.ni.pmf.web.issuetracker.repository;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.ZonedDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import rs.ac.ni.pmf.web.issuetracker.model.entity.IssueEntity;
import rs.ac.ni.pmf.web.issuetracker.model.entity.ProjectEntity;

@DataJpaTest
	//@SpringBootTest
class RepositoriesIT
{
	@Autowired
	private ProjectsRepository _projectsRepository;

	@Autowired
	private IssuesRepository _issuesRepository;

	@Test
	void testDatabase()
	{
		final ProjectEntity projectEntity = ProjectEntity.builder()
			.name("The test Project")
			.startDate(ZonedDateTime.now())
			.build();

		final ProjectEntity savedProject = _projectsRepository.save(projectEntity);

		final IssueEntity issueEntity = IssueEntity.builder()
			.summary("Issue 1")
			.description("Serious issue")
			.project(savedProject)
			.build();

		final IssueEntity savedIssue = _issuesRepository.save(issueEntity);

		assertThat(savedIssue.getProject()).isEqualTo(savedProject);
	}

	@Test
	public void shouldInsertProjectWithNewIssues()
	{
		final ProjectEntity projectEntity = ProjectEntity.builder()
			.name("The test Project")
			.startDate(ZonedDateTime.now())
			.build();

		assertThat(projectEntity.getId()).isNull();

		final IssueEntity issueEntity1 = IssueEntity.builder()
			.summary("Issue 1")
			.description("Serious issue")
			.build();

		final IssueEntity issueEntity2 = IssueEntity.builder()
			.summary("Issue 2")
			.description("Serious issue")
			.build();

		projectEntity.addIssues(issueEntity1, issueEntity2);

		final ProjectEntity savedEntity = _projectsRepository.save(projectEntity);

		assertThat(savedEntity.getId()).isNotNull();
		assertThat(_issuesRepository.count()).isEqualTo(2);

		_projectsRepository.delete(savedEntity);
		assertThat(_issuesRepository.count()).isEqualTo(0);
	}
}
