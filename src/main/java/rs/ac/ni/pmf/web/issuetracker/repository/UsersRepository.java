package rs.ac.ni.pmf.web.issuetracker.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.ni.pmf.web.issuetracker.model.entity.UserEntity;

public interface UsersRepository extends JpaRepository<UserEntity, UUID>
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
}
