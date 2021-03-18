package rs.ac.ni.pmf.web.issuetracker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import lombok.extern.slf4j.Slf4j;
import rs.ac.ni.pmf.web.issuetracker.model.entity.IssueEntity;
import rs.ac.ni.pmf.web.issuetracker.model.entity.ProjectEntity;
import rs.ac.ni.pmf.web.issuetracker.service.ProjectsService;

@SpringBootApplication
@Slf4j
public class IssueTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(IssueTrackerApplication.class, args);
	}

	@Bean
	public CommandLineRunner gerRunner(final ProjectsService projectsService)
	{
		return args -> {

			projectsService.showProjects();
		};
	}

	private void initializeData(final ProjectsService projectsService)
	{
		final ProjectEntity project1 = projectsService.addProject("Project 1");
		final ProjectEntity project2 = projectsService.addProject("Project 2");

		final IssueEntity issue1 = projectsService.addIssue("Test1", "test", project1);
		log.info("Added issue {}", issue1.getId());

		final IssueEntity issue2 = projectsService.addIssue("Test2", "test", project1);
		log.info("Added issue {}", issue2.getId());
		final IssueEntity issue3 = projectsService.addIssue("Test3", "test", project1);
		log.info("Added issue {}", issue3.getId());

		final IssueEntity issue4 = projectsService.addIssue("Test4", "test", project2);
		log.info("Added issue {}", issue4.getId());
		final IssueEntity issue5 = projectsService.addIssue("Test5", "test", project2);
		log.info("Added issue {}", issue5.getId());
	}
}
