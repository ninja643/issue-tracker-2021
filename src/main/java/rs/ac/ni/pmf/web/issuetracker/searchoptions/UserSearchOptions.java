package rs.ac.ni.pmf.web.issuetracker.searchoptions;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserSearchOptions
{
	String firstName;
	String lastName;
}
