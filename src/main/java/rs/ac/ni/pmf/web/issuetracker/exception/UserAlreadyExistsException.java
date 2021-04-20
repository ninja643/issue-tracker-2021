package rs.ac.ni.pmf.web.issuetracker.exception;

public class UserAlreadyExistsException extends RuntimeException
{
	public UserAlreadyExistsException(String message)
	{
		super(message);
	}
}
