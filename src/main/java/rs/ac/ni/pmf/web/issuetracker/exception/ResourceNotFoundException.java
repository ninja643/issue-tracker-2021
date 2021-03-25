package rs.ac.ni.pmf.web.issuetracker.exception;

public class ResourceNotFoundException extends RuntimeException
{
	public ResourceNotFoundException(final String message)
	{
		super(message);
	}
}
