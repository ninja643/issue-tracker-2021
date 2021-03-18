package rs.ac.ni.pmf.web.issuetracker.repository;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import rs.ac.ni.pmf.web.issuetracker.model.entity.*;

@DataJpaTest
class RepositoriesIT
{
	@Autowired
	private ProjectsRepository _projectsRepository;

	@Autowired
	private IssuesRepository _issuesRepository;

	@Autowired
	private UsersRepository _usersRepository;

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

	@Test
	public void shouldAssignUserToProject()
	{
		final UserEntity userEntity = UserEntity.builder()
			.firstName("Marko")
			.lastName("Milosevic")
			.username("markom")
			.password("pass123")
			.build();

		final UserEntity savedUser = _usersRepository.save(userEntity);
		System.out.println(savedUser.getId().toString());

		final ProjectEntity projectEntity = _projectsRepository.save(ProjectEntity.builder()
			.name("Test")
			.users(Arrays.asList(savedUser))
			.build());

		System.out.println(projectEntity.getUsers().size());

		final ProjectEntity project = _projectsRepository.findById(projectEntity.getId())
			.orElseThrow(() -> new RuntimeException());

		System.out.println(project.getUsers().size());
		System.out.println(project.getUsers().get(0).getFirstName());
	}
}
