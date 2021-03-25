package rs.ac.ni.pmf.web.issuetracker.exception;

public class ProjectNotFoundException extends ResourceNotFoundException
{
	public ProjectNotFoundException(final String message)
	{
		super(message);
	}
}
