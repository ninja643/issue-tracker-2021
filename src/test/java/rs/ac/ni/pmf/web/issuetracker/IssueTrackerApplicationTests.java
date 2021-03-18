package rs.ac.ni.pmf.web.issuetracker;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import javax.persistence.Entity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rs.ac.ni.pmf.web.issuetracker.model.entity.IssueEntity;
import rs.ac.ni.pmf.web.issuetracker.model.entity.ProjectEntity;
import rs.ac.ni.pmf.web.issuetracker.repository.IssuesRepository;
import rs.ac.ni.pmf.web.issuetracker.repository.ProjectsRepository;

@SpringBootTest
class IssueTrackerApplicationTests
{

	@Autowired
	private ProjectsRepository _projectsRepository;

	@Autowired
	private IssuesRepository _issuesRepository;

	@Test
	void testDatabase()
	{
		final ProjectEntity projectEntity = ProjectEntity.builder()
			.name("Test Project")
			.startDate(ZonedDateTime.now())
			.build();

		final ProjectEntity savedProject = _projectsRepository.save(projectEntity);

		final IssueEntity issueEntity = IssueEntity.builder()
			.summary("Issue 1")
			.description("Serious issue")
			.project(savedProject)
			.build();

		final IssueEntity savedIssue = _issuesRepository.save(issueEntity);

		ZonedDateTime time = savedProject.getModifiedOn();
		System.out.println(time.format(DateTimeFormatter.ofPattern("yyyy-MMM-dd")));

		System.out.println(savedProject.toString());
		System.out.println(savedIssue.toString());
	}
}
