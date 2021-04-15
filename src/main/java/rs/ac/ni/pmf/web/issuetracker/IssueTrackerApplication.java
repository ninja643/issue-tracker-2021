package rs.ac.ni.pmf.web.issuetracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@Slf4j
public class IssueTrackerApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(IssueTrackerApplication.class, args);
	}
}
