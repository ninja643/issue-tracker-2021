package rs.ac.ni.pmf.web.issuetracker.model.entity;

import java.time.ZonedDateTime;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.*;

@Entity
@Table(name = "projects")
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

	@OneToMany(mappedBy = "project")
	List<IssueEntity> issues;
}
