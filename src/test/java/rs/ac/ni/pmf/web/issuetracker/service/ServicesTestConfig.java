package rs.ac.ni.pmf.web.issuetracker.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ UsersService.class, ProjectsService.class })
public class ServicesTestConfig
{
}
