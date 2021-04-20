package rs.ac.ni.pmf.web.issuetracker.rest;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import rs.ac.ni.pmf.web.issuetracker.model.Role;
import rs.ac.ni.pmf.web.issuetracker.model.dto.UserDTO;

@RequestMapping("/users")
public interface UsersRestController
{
	@GetMapping("/{username}")
	public UserDTO getUser(@PathVariable final String username);

	@GetMapping("/{username}/roles")
	public List<Role> getUserRoles(@PathVariable final String username);
}
