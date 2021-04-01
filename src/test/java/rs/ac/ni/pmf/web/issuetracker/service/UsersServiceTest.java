package rs.ac.ni.pmf.web.issuetracker.service;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import rs.ac.ni.pmf.web.issuetracker.model.entity.ProjectEntity;
import rs.ac.ni.pmf.web.issuetracker.model.entity.UserEntity;
import rs.ac.ni.pmf.web.issuetracker.searchoptions.UserSearchOptions;

@DataJpaTest
@Import(ServicesTestConfig.class)
class UsersServiceTest
{
	@Autowired
	private UsersService _usersService;

	@Autowired
	private ProjectsService _projectsService;

	@BeforeEach
	public void initializeData()
	{
		final ProjectEntity project1 = _projectsService.addProject("Project1");
		final ProjectEntity project2 = _projectsService.addProject("Project2");

		_usersService.add(UserEntity.builder()
			.username("pera")
			.password("pera")
			.firstName("Pera")
			.lastName("Peric")
			.projects(Arrays.asList(project1))
			.build());
		_usersService.add(UserEntity.builder()
			.username("mika")
			.password("mika")
			.firstName("Mika")
			.lastName("Peric")
			.projects(Arrays.asList(project2))
			.build());
		_usersService.add(UserEntity.builder()
			.username("pera2")
			.password("pera")
			.firstName("Pera")
			.lastName("Mikic")
			.projects(Arrays.asList(project1, project2))
			.build());
		_usersService.add(UserEntity.builder()
			.username("mika2")
			.password("pera")
			.firstName("Mika")
			.lastName("Mikic")
			.projects(Arrays.asList(project2))
			.build());
	}

	@Test
	public void shouldFindUserByUsername()
	{
		final UserEntity userEntity = _usersService.findByUsername("pera");
		assertThat(userEntity.getFirstName()).isEqualTo("Pera");
		assertThat(userEntity.getLastName()).isEqualToIgnoringCase("peric");
	}

	@Test
	public void shouldSearchUsers()
	{
		final UserSearchOptions firstNameOptions = UserSearchOptions.builder().firstName("Pera").build();
		final UserSearchOptions lastNameOptions = UserSearchOptions.builder().lastName("Peric").build();
		final UserSearchOptions fullNameOptions = UserSearchOptions.builder().firstName("Pera").lastName("Peric").build();

		assertThat(_usersService.findUsers(firstNameOptions)).hasSize(2);
		assertThat(_usersService.findUsers(lastNameOptions)).hasSize(2);
		assertThat(_usersService.findUsers(fullNameOptions)).hasSize(1);
	}

	@Test
	public void shouldFindByProject()
	{
		assertThat(_usersService.findByProject("Project1")).hasSize(2);
		assertThat(_usersService.findByProject("Project2")).hasSize(3);

		assertThat(_usersService.searchByProjectAndOptions("Project2", UserSearchOptions.builder().lastName("Peric").build()))
			.hasSize(1);
	}

	@Test
	public void shouldFindByProjectCount()
	{
		assertThat(_usersService.minProjects(2)).hasSize(1);
		assertThat(_usersService.minProjects(1)).hasSize(4);

//		assertThat(_usersService.minProjectsAndSearch(2, UserSearchOptions.builder().firstName("Pera").lastName("Peric").build()))
//			.hasSize(2);
	}
}