package rs.ac.ni.pmf.web.issuetracker.service;

import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.issuetracker.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.issuetracker.model.entity.UserEntity;
import rs.ac.ni.pmf.web.issuetracker.repository.UsersRepository;

@Service
@RequiredArgsConstructor
public class UsersService
{
	private final UsersRepository _usersRepository;

	public List<UserEntity> getUsers()
	{
		return _usersRepository.findAll();
	}

	public UserEntity add(final UserEntity userEntity)
	{
		return _usersRepository.save(userEntity);
	}

	public UserEntity findByUsername(final String username)
	{
		return _usersRepository.findByUsername(username)
			.orElseThrow(() -> new ResourceNotFoundException("User '" + username + "' does not exist"));
	}
}
