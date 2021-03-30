package rs.ac.ni.pmf.web.issuetracker.model.entity;

import java.util.*;
import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity
{
	@Id
	@GeneratedValue
	UUID id;

	String firstName;
	String lastName;

	@Column(name = "username")
	String username;
	String password;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "users_projects",
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "project_id")
	)
	@Builder.Default
	List<ProjectEntity> projects = new ArrayList<>();
}
