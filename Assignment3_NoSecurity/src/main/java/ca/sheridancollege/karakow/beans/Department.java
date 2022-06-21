package ca.sheridancollege.karakow.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

	private Long DEPARTMENT_ID;
	@NonNull
	private String DEPARTMENT_NAME;
	private Long MANAGER_ID;
	private Long LOCATION_ID;
}
