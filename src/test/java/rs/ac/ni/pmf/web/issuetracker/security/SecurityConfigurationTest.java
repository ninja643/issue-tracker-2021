package rs.ac.ni.pmf.web.issuetracker.security;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import rs.ac.ni.pmf.web.issuetracker.model.dto.ProjectDTO;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SecurityConfigurationTest
{
	private static final ProjectDTO testProject = ProjectDTO.builder().name("Test Project").build();

	@LocalServerPort
	private int _port;

	private String _baseUrl;

	@BeforeEach
	public void init()
	{
		_baseUrl = "http://localhost:" + _port;
	}

	@Test
	public void shouldCheckAnonymousCalls()
	{
		final TestRestTemplate testRestTemplate = new TestRestTemplate();

		final ResponseEntity<String> response = testRestTemplate.getForEntity(_baseUrl + "/projects", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		final ResponseEntity<Problem> responseBadAuth = testRestTemplate.getForEntity(_baseUrl + "/projects/3001", Problem.class);
		assertThat(responseBadAuth.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
		final Problem problem = responseBadAuth.getBody();
		assertThat(problem.getTitle()).isEqualTo("Authentication failed");
		assertThat(problem.getDetail()).isEqualTo("Full authentication is required to access this resource");
	}

	@Test
	public void shouldCheckForUser()
	{
		final TestRestTemplate testRestTemplate = new TestRestTemplate("user", "password");

		final ResponseEntity<List> response = testRestTemplate.getForEntity(_baseUrl + "/projects", List.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEmpty();

		final ResponseEntity<Problem> responseNoProject =
			testRestTemplate.getForEntity(_baseUrl + "/projects/3001", Problem.class);
		assertThat(responseNoProject.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(responseNoProject.getBody().getDetail()).isEqualTo("Project with id '3001' does not exist");

		final ResponseEntity<Problem> responseCreate =
			testRestTemplate.postForEntity(_baseUrl + "/projects", testProject, Problem.class);
		assertThat(responseCreate.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
	}

	@Test
	public void shouldCheckForAdmin()
	{
		final TestRestTemplate testRestTemplate = new TestRestTemplate("admin", "admin");
		final ResponseEntity<ProjectDTO> responseCreate =
			testRestTemplate.postForEntity(_baseUrl + "/projects", testProject, ProjectDTO.class);

		assertThat(responseCreate.getStatusCode()).isEqualTo(HttpStatus.OK);
		final ProjectDTO createdProject = responseCreate.getBody();

		assertThat(createdProject.getName()).isEqualTo(testProject.getName());

		final ResponseEntity<List> response = testRestTemplate.getForEntity(_baseUrl + "/projects", List.class);
		assertThat(response.getBody()).hasSize(1);

		final ResponseEntity<ProjectDTO> responseProject =
			testRestTemplate.getForEntity(_baseUrl + "/projects/" + createdProject.getId(), ProjectDTO.class);

		assertThat(responseProject.getBody()).isNotNull();
	}
}
