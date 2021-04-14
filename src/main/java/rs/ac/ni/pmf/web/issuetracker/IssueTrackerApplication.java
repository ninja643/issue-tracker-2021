package rs.ac.ni.pmf.web.issuetracker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import lombok.extern.slf4j.Slf4j;
import rs.ac.ni.pmf.web.issuetracker.model.entity.IssueEntity;
import rs.ac.ni.pmf.web.issuetracker.model.entity.ProjectEntity;
import rs.ac.ni.pmf.web.issuetracker.service.ProjectsService;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@Slf4j
public class IssueTrackerApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(IssueTrackerApplication.class, args);
	}
}
