package rs.ac.ni.pmf.web.issuetracker.repository.specification;

import java.util.*;
import javax.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import rs.ac.ni.pmf.web.issuetracker.model.entity.*;
import rs.ac.ni.pmf.web.issuetracker.searchoptions.UserSearchOptions;

public class UsersSpecifications
{
	public static Specification<UserEntity> search(final UserSearchOptions options)
	{
		return (root, query, criteriaBuilder) -> {
			final List<Predicate> predicates = new ArrayList<>();
			final Path<String> firstName = root.get(UserEntity_.firstName);
			final Path<String> lastName = root.get(UserEntity_.lastName);

			if (StringUtils.hasText(options.getFirstName()))
			{
				predicates.add(criteriaBuilder.equal(firstName, options.getFirstName()));
			}

			if (StringUtils.hasText(options.getLastName()))
			{
				predicates.add(criteriaBuilder.equal(lastName, options.getLastName()));
			}

			return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
		};
	}

	public static Specification<UserEntity> findByProject(final String name)
	{
		return ((root, query, criteriaBuilder) -> {

			final Path<String> projectName = root.join(UserEntity_.projects).get(ProjectEntity_.name);

			return criteriaBuilder.equal(projectName, name);
		});
	}

	public static Specification<UserEntity> minimalProjectsCount(final int count)
	{
		return ((root, query, criteriaBuilder) -> {

			final Path<UUID> userId = root.get(UserEntity_.id);

			final ListJoin<UserEntity, ProjectEntity> join = root.join(UserEntity_.projects);
			final Path<Long> projectId = join.get(ProjectEntity_.id);
			final Path<String> projectName = join.get(ProjectEntity_.name);

			query.groupBy(userId);
			query.having(criteriaBuilder.ge(criteriaBuilder.count(projectId), count));

			return criteriaBuilder.isNotNull(projectName);
		});
	}
}
