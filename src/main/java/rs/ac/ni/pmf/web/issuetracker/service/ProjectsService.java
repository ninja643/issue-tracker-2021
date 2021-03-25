package rs.ac.ni.pmf.web.issuetracker.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.issuetracker.exception.ProjectNotFoundException;
import rs.ac.ni.pmf.web.issuetracker.model.entity.IssueEntity;
import rs.ac.ni.pmf.web.issuetracker.model.entity.ProjectEntity;
import rs.ac.ni.pmf.web.issuetracker.repository.IssuesRepository;
import rs.ac.ni.pmf.web.issuetracker.repository.ProjectsRepository;

@Service
@RequiredArgsConstructor
public class ProjectsService
{
	private final ProjectsRepository _projectsRepository;
	private final IssuesRepository _issuesRepository;

	@Transactional
	public void showProjects()
	{
		List<ProjectEntity> projectEntities = _projectsRepository.findAll();

		for (final ProjectEntity projectEntity : projectEntities)
		{
			System.out.println("Project: " + projectEntity.getName());
			System.out.println("Issues: ");
			for (final IssueEntity issueEntity : projectEntity.getIssues())
			{
				System.out.println(" - " + issueEntity.getSummary());
			}
		}
	}

	public ProjectEntity addProject(final String projectName)
	{
		final ProjectEntity projectEntity = ProjectEntity.builder().name(projectName).build();

		return _projectsRepository.save(projectEntity);
	}

	public IssueEntity addIssue(final String summary, final String description, final ProjectEntity projectEntity)
	{
		final IssueEntity issueEntity =
			IssueEntity.builder().summary(summary).description(description).project(projectEntity).build();

		return _issuesRepository.save(issueEntity);
	}

	public ProjectEntity findByProjectName(final String projectName)
	{
//		final ProjectEntity entity = _projectsRepository.findByName(projectName);
//
//		if (entity != null)
//		{
//			return entity;
//		}
//
//		throw new ProjectNotFoundException("Project '" + projectName + "' does not exist");

		return _projectsRepository.findByName(projectName)
			.orElseThrow(() -> new ProjectNotFoundException("Project '" + projectName + "' does not exist"));
	}
}
