package tw.intelegence.ncsist.sstp.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class NettyDTO implements Serializable {


	private String username;

	private String name;

	private Long level;

	private Long grade;

	private String studentUnit;

	private String ip;

	private Long studentId;

	private Long studentBatch;
}
