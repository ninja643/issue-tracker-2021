package rs.ac.ni.pmf.web.issuetracker.model.dto;

import java.time.ZonedDateTime;
import lombok.*;

@Value
@Builder
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class ProjectDTO
{
	Long id;
	String name;
	ZonedDateTime startDate;
	ZonedDateTime modifiedOn;
}
