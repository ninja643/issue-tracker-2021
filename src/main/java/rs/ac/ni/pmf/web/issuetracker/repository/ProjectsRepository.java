package rs.ac.ni.pmf.web.issuetracker.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.ni.pmf.web.issuetracker.model.entity.ProjectEntity;

public interface ProjectsRepository extends JpaRepository<ProjectEntity, Long>
{
//	ProjectEntity findByName(String name);

	Optional<ProjectEntity> findByName(String name);
}
