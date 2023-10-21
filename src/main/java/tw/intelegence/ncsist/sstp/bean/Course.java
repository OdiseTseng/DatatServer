package tw.intelegence.ncsist.sstp.bean;

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
@Table(name = "course")
@Schema(name = "課程", description = "課程資料")
public class Course implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Primary Key")
	private Long id;

	@Schema(description = "課程編號", example = "20230101001")
	private Long courseId;

	@Schema(description = "課程類型", example = "1=單人課程,2=多人課程")
	private Long courseType;

	@Schema(description = "課程名稱", example = "如何啟動教學軟體")
	private String courseName;

	@Schema(description = "課程時間", example = "15 = 15分鐘")
	private Long courseSchedule;

	@Schema(description = "課程描述", example = "教導與學習正確啟動教學軟體")
	private String courseDesc;

	@Schema(description = "學分數", example = "1")
	private Long creditUnits;

	@Schema(description = "單元列表", example = "單元1、單元2")
	private String unitList;

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
