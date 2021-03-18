package rs.ac.ni.pmf.web.issuetracker.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.ni.pmf.web.issuetracker.model.entity.UserEntity;

public interface UsersRepository extends JpaRepository<UserEntity, UUID>
{
}
