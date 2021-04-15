package rs.ac.ni.pmf.web.issuetracker.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
	private final ObjectMapper _objectMapper;

	public static final String USER = "USER";
	public static final String ADMIN = "ADMIN";

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		final PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

		auth
			.inMemoryAuthentication()
			.withUser("user")
			.password(encoder.encode("password"))
			.authorities(USER)
			.and()
			.withUser("admin")
			.password(encoder.encode("admin"))
			.authorities(USER, ADMIN);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/projects", "/projects/").permitAll()
			.antMatchers(HttpMethod.GET, "/projects/**").authenticated()
			.antMatchers(HttpMethod.POST, "/projects", "/projects/").hasAuthority(ADMIN)
			.anyRequest().authenticated()
			.and()
			.exceptionHandling()
			.accessDeniedHandler((request, response, accessDeniedException) -> {
				response.setStatus(HttpStatus.FORBIDDEN.value());
				response.setContentType("application/json");

				final String path = request.getMethod() + " " + request.getServletPath();

				final Problem problem = Problem.create()
					.withStatus(HttpStatus.FORBIDDEN)
					.withTitle("You do not have rights to access the requested resource: " + path)
					.withDetail(accessDeniedException.getMessage());

				response.getOutputStream().println(_objectMapper.writeValueAsString(problem));
			})
			.and()
			.httpBasic()
			.authenticationEntryPoint((request, response, authException) -> {
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				response.setContentType("application/json");

				final Problem problem = Problem.create()
					.withStatus(HttpStatus.UNAUTHORIZED)
					.withTitle("Authentication failed")
					.withDetail(authException.getMessage());

				response.getOutputStream().println(_objectMapper.writeValueAsString(problem));
			})
		;
	}
}
