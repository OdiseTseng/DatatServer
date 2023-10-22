package tw.intelegence.ncsist.sstp.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class NettyDTO implements Serializable {


	private String username;

	private String name;

	private String level;

	private String ip;

	private Long studentId;

	private Long studentBatch;
}
