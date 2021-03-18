package rs.ac.ni.pmf.web.issuetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.ni.pmf.web.issuetracker.model.entity.IssueEntity;

public interface IssuesRepository extends JpaRepository<IssueEntity, Long>
{
}
