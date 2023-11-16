package tw.intelegence.ncsist.sstp.bean;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.sql.Date;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "attendance")
@Schema(name = "上課紀錄", description = "個別學員上課的紀錄")
public class Attendance implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Primary Key")
	private Long id;

	@Schema(description = "使用者帳號", example = "test1")
	@Column
	private String username;

	@Schema(description = "紀錄編號", example = "20230000001")
	@Column
	private Long attendanceId;

	@Schema(description = "課程編號", example = "2023001")
	@Column
	private Long courseId;

	@Schema(description = "單元編號", example = "001001")
	@Column
	private Long unitId;

	@Schema(description = "內容編號", example = "001001001")
	@Column
	private Long contentId;

	@Schema(description = "測驗編號", example = "001001001001")
	@Column
	private Long quizId;

	@Schema(description = "上課日期", example = "2023-10-03T10:50:02:231Z")
	@Column
	private Date attendanceDate;

	@Schema(description = "隊伍編號", example = "1")
	@Column
	private Long team;

	@Schema(description = "角色編號", example = "001=操作手，002=通訊兵，003=車長，004=指揮所，005=教官")
	@Column
	private Long role;

	@Schema(description = "紀錄成績", example = "001")
	@Column
	private Long recordScore;

	@Schema(description = "紀錄快照", example = "圖片格式")
	@Column
	private String recordShot;

	@Schema(description = "成績", example = "001")
	@Column
	private Long score;

	@Schema(description = "狀態", example = "000=未啟動，001=啟用，002=暫停，003=停止使用")
	@Column
	private Long state;

	@Schema(description = "時間數字", example = "1696301407620")
	@Column
	private Long longDate;

	@Schema(description = "建立時間", example = "2023-10-03T10:50:02:231Z")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", nullable = false, updatable = false)
	@CreationTimestamp
	private java.util.Date createTime;

	@Schema(description = "更新時間", example = "2023-10-03T10:50:02:231Z")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time", nullable = false, updatable = true)
	@CreationTimestamp
	private java.util.Date updateTime;

}
