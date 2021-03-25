package rs.ac.ni.pmf.web.issuetracker.model.dto;

import java.time.ZonedDateTime;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProjectDTO
{
	Long id;
	String name;
	ZonedDateTime startDate;
	ZonedDateTime modifiedOn;
}
