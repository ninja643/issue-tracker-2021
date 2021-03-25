package rs.ac.ni.pmf.web.issuetracker.repository;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import rs.ac.ni.pmf.web.issuetracker.model.entity.UserEntity;

@DataJpaTest
class UsersRepositoryTest
{
	@Autowired
	private UsersRepository _usersRepository;

	@BeforeEach
	public void initializeData()
	{
		_usersRepository.save(UserEntity.builder()
			.username("pera")
			.password("pera")
			.firstName("Pera")
			.lastName("Peric")
			.build());
		_usersRepository.save(UserEntity.builder()
			.username("mika")
			.password("mika")
			.firstName("Mika")
			.lastName("Peric")
			.build());
		_usersRepository.save(UserEntity.builder()
			.username("pera2")
			.password("pera")
			.firstName("Pera")
			.lastName("Mikic")
			.build());
		_usersRepository.save(UserEntity.builder()
			.username("mika2")
			.password("pera")
			.firstName("Mika")
			.lastName("Mikic")
			.build());
	}

	@Test
	public void shouldTestFindBy()
	{
		assertThat(_usersRepository.findByFirstName("Pera")).hasSize(2);
		assertThat(_usersRepository.findByFirstNameAndLastName("Pera", "Peric")).hasSize(1);
		assertThat(_usersRepository.findByFirstNameOrLastName("Pera", "Peric")).hasSize(3);

		assertThat(_usersRepository.findByFirstNameIgnoreCase("pera")).hasSize(2);
	}

	@Test
	public void shouldGetSorted()
	{
		assertThat(_usersRepository.findByFirstNameOrderByLastNameDesc("Pera").stream()
			.map(UserEntity::getLastName)
			.collect(Collectors.toList()))
			.containsExactly("Peric", "Mikic");

		assertThat(_usersRepository.findByFirstNameOrderByLastName("Pera").stream()
			.map(UserEntity::getLastName)
			.collect(Collectors.toList()))
			.containsExactly("Mikic", "Peric");
	}

	@Test
	public void shouldFilterUsernames()
	{
		assertThat(_usersRepository.findByUsernameLike("m%")).hasSize(2);
	}

	@Test
	public void shouldFindComplicated()
	{
		_usersRepository.findByFirstNameIgnoreCaseOrLastNameIgnoreCaseOrderByFirstNameDescLastNameAsc("pera", "peric");
	}
}