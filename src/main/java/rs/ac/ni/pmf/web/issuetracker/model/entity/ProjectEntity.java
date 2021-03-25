package rs.ac.ni.pmf.web.issuetracker.model.entity;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.*;

@Entity
@Table(name = "projects", uniqueConstraints = { @UniqueConstraint(columnNames = "name") })
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectEntity
{
	@Id
	@GeneratedValue
	Long id;

	String name;

	ZonedDateTime startDate;

	@UpdateTimestamp
	ZonedDateTime modifiedOn;

	@OneToMany(mappedBy = "project", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@Builder.Default
	List<IssueEntity> issues = new ArrayList<>();

	@ManyToMany(mappedBy = "projects", cascade = CascadeType.ALL)
	@Builder.Default
	List<UserEntity> users = new ArrayList<>();

	public void addIssues(IssueEntity... entities)
	{
		for (final IssueEntity issueEntity : entities)
		{
			issueEntity.setProject(this);
			issues.add(issueEntity);
		}
	}
}
