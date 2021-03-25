package rs.ac.ni.pmf.web.issuetracker.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import rs.ac.ni.pmf.web.issuetracker.model.entity.UserEntity;

@DataJpaTest
@Import(ServicesTestConfig.class)
class UsersServiceTest
{
	@Autowired
	private UsersService _usersService;

	@BeforeEach
	public void initializeData()
	{
		_usersService.add(UserEntity.builder()
			.username("pera")
			.password("pera")
			.firstName("Pera")
			.lastName("Peric")
			.build());
		_usersService.add(UserEntity.builder()
			.username("mika")
			.password("mika")
			.firstName("Mika")
			.lastName("Peric")
			.build());
		_usersService.add(UserEntity.builder()
			.username("pera2")
			.password("pera")
			.firstName("Pera")
			.lastName("Mikic")
			.build());
		_usersService.add(UserEntity.builder()
			.username("mika2")
			.password("pera")
			.firstName("Mika")
			.lastName("Mikic")
			.build());
	}

	@Test
	public void shouldFindUserByUsername()
	{
		final UserEntity userEntity = _usersService.findByUsername("pera");
		assertThat(userEntity.getFirstName()).isEqualTo("Pera");
		assertThat(userEntity.getLastName()).isEqualToIgnoringCase("peric");
	}
}