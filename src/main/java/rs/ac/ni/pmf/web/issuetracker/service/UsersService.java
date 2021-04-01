package rs.ac.ni.pmf.web.issuetracker.service;

import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.issuetracker.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.web.issuetracker.model.entity.UserEntity;
import rs.ac.ni.pmf.web.issuetracker.repository.UsersRepository;
import rs.ac.ni.pmf.web.issuetracker.repository.specification.UsersSpecifications;
import rs.ac.ni.pmf.web.issuetracker.searchoptions.UserSearchOptions;

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

	public List<UserEntity> findUsers(final UserSearchOptions userSearchOptions)
	{
		return _usersRepository.findAll(UsersSpecifications.search(userSearchOptions));
	}

	public List<UserEntity> findByProject(final String projectName)
	{
		return _usersRepository.findAll(UsersSpecifications.findByProject(projectName));
	}

	public List<UserEntity> searchByProjectAndOptions(final String projectName, final UserSearchOptions userSearchOptions)
	{
		return _usersRepository
			.findAll(UsersSpecifications.findByProject(projectName).and(UsersSpecifications.search(userSearchOptions)));
	}

	public List<UserEntity> minProjects(int count)
	{
		return _usersRepository.findAll(UsersSpecifications.minimalProjectsCount(count));
	}

//	public List<UserEntity> minProjectsAndSearch(int count, UserSearchOptions options)
//	{
//		return _usersRepository.findAll(UsersSpecifications.minimalProjectsCount(count)
//			.or(UsersSpecifications.search(options)));
//	}
}
