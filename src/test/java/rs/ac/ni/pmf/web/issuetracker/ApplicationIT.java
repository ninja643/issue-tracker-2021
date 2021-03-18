//package rs.ac.ni.pmf.web.issuetracker;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.TestPropertySource;
//import rs.ac.ni.pmf.web.issuetracker.repository.IssuesRepository;
//import rs.ac.ni.pmf.web.issuetracker.repository.ProjectsRepository;
//
//@SpringBootTest
//@TestPropertySource("/it_tests.properties")
//public class ApplicationIT
//{
//	@Autowired
//	private ProjectsRepository _projectsRepository;
//
//	@Autowired
//	private IssuesRepository _issuesRepository;
//
//	@BeforeEach
//	public void initializeDatabase()
//	{
//		_issuesRepository.deleteAll();
//		_projectsRepository.deleteAll();
//	}
//
//	@Test
//	public void shouldCountProjects()
//	{
//		assertThat(_projectsRepository.count()).isEqualTo(0);
//	}
//}
