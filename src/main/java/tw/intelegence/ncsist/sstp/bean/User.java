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

	@Schema(description = "學員層級", example = "1001=管理者，1002=教官，1003=指揮學員，1004=一般學員")
	private Long level;

	@Schema(description = "學員職級", example = "2000=將軍，2001=上校，2002=中校，2003=少校，2004=上尉，2005=中尉，2006=少尉，2007=一等士官長，2008=二等士官長，2009=三等士官長，2010=上士，2011=中士，2012=下士，2013=上兵，2014=一兵，2015=二兵")
	private Long grade;

	@Schema(description = "學員職位", example = "防衛官")
	private String studentWork;

	@Schema(description = "學員單位", example = "陸軍作戰指揮部")
	private String studentUnit;

	@Schema(description = "學員單位代碼", example = "996")
	private int studentUnitCode;

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
