package rs.ac.ni.pmf.web.issuetracker.model.entity;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "issues")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IssueEntity
{
	@Id
	@GeneratedValue
	Long id;

	String summary;

	String description;

	@ManyToOne
	@JoinColumn(name = "project_id")
	ProjectEntity project;
}
