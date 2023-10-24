package tw.intelegence.ncsist.sstp.model;

import lombok.*;

import java.io.Serializable;

@Data
public class UserDTO implements Serializable {


	private String username;

	private String password;

	private String ctx;
}
