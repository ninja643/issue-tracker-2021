package rs.ac.ni.pmf.web.issuetracker.rest.impl;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rs.ac.ni.pmf.web.issuetracker.exception.ResourceNotFoundException;

@ControllerAdvice
@ResponseBody
public class ErrorController
{
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Problem handleResourceNotFound(final ResourceNotFoundException e)
	{
		return Problem.create()
			.withStatus(HttpStatus.NOT_FOUND)
			.withTitle("Resource not found")
			.withDetail(e.getMessage());
	}

	@ExceptionHandler(DataAccessException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Problem handleDataAccessException(final DataAccessException e)
	{
		return Problem.create()
			.withTitle("Error accessing data")
			.withDetail(e.getMostSpecificCause().getMessage());
	}
}
