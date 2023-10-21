package tw.intelegence.ncsist.sstp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class CourseDTO implements Serializable {


	private Long courseType;

	private String courseName;

	private Long courseSchedule;

	private String courseDesc;

	private Long creditUnits;

	private String unitList;
}
