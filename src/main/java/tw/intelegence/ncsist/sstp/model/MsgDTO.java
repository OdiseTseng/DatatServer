package tw.intelegence.ncsist.sstp.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class MsgDTO implements Serializable {

	private int cmd;

	private String from;

	private String msg;
}
