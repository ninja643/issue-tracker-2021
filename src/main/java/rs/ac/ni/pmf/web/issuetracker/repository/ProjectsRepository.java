package rs.ac.ni.pmf.web.issuetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.ni.pmf.web.issuetracker.model.entity.ProjectEntity;

public interface ProjectsRepository extends JpaRepository<ProjectEntity, Long>
{
}
