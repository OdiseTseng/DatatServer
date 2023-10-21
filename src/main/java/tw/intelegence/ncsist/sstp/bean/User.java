package tw.intelegence.ncsist.sstp.bean;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "user")
@Schema(name = "使用者", description = "使用者檔案")
public class User implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Primary Key")
	private Long id;

	@Schema(description = "使用者帳號", example = "test1")
	private String username;

	@Schema(description = "使用者密碼", example = "0000")
	private String password;

	@Schema(description = "使用者名稱", example = "測試者")
	private String name;

	@Schema(description = "第三方認證金鑰", example = "")
	private String oAuthKey;

	@Schema(description = "學號", example = "2075001")
	private Long studentId;

	@Schema(description = "學員梯次", example = "2023001")
	private Long studentBatch;

	@Schema(description = "職級", example = "1001=管理者，1002=教官，1003=指揮學員，1004=一般學員")
	private Long level;

	@Schema(description = "登錄IP", example = "192.168.0.100")
	private String ip;

	@Schema(description = "狀態", example = "000=未啟動，001=啟用，002=暫停，003=停止使用")
	private Long state;

	@Schema(description = "時間數字", example = "1696301407620")
	private Long longDate;

	@Schema(description = "建立時間", example = "2023-10-03T10:50:02:231Z")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", nullable = false, updatable = false)
	@CreationTimestamp
	private Date createTime;

	@Schema(description = "更新時間", example = "2023-10-03T10:50:02:231Z")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time", nullable = false, updatable = true)
	@CreationTimestamp
	private Date updateTime;

}
