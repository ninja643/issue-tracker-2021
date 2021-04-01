package rs.ac.ni.pmf.web.issuetracker.repository;

import java.util.*;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import rs.ac.ni.pmf.web.issuetracker.model.entity.UserEntity;

public interface UsersRepository extends JpaRepository<UserEntity, UUID>, JpaSpecificationExecutor<UserEntity>
{
	Optional<UserEntity> findByUsername(String username);

	List<UserEntity> findByFirstName(String firstName);

	List<UserEntity> findByFirstNameAndLastName(String firstName, String lastName);

	List<UserEntity> findByFirstNameOrLastName(String firstName, String lastName);

	List<UserEntity> findByFirstNameIgnoreCase(String firstName);

	List<UserEntity> findByFirstNameOrderByLastName(String firstName);

	List<UserEntity> findByFirstNameOrderByLastNameDesc(String firstName);

	List<UserEntity> findByUsernameLike(String filter);

	List<UserEntity> findByFirstNameIgnoreCaseOrLastNameIgnoreCaseOrderByFirstNameDescLastNameAsc(String firstName, String lastName);

	@Query("select u from UserEntity as u where u.username = :usernameParam")
	UserEntity getMeAUser(@Param("usernameParam") String username);

	@Query("select u from UserEntity u where u.firstName like ?1%")
	List<UserEntity> getSomeUsers(String namePrefix, Sort sort);

	@Modifying(flushAutomatically = true, clearAutomatically = true)
	@Query("update UserEntity u set u.firstName = :updatedFirstName where u.username = :username")
	int updateFirstName(@Param("username") String username, @Param("updatedFirstName") String name);
}
