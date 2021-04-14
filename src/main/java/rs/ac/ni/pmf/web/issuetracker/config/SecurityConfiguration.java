package rs.ac.ni.pmf.web.issuetracker.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{

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
			.httpBasic();
	}
}
