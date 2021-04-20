package rs.ac.ni.pmf.web.issuetracker.model.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDTO
{
	private String firstName;
	private String lastName;

	private String username;
	private String password;
}
