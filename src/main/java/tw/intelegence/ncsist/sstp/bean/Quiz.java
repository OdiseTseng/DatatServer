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
@Table(name = "quiz")
@Schema(name = "測驗", description = "測驗的內容本體")
public class Quiz implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Primary Key")
	private Long id;

	@Schema(description = "測驗編號", example = "001001001")
	private Long quizId;

	@Schema(description = "單元編號", example = "001001")
	private Long unitId;

	@Schema(description = "測驗標題", example = "問答題")
	private String title;

	@Schema(description = "測驗內容", example = "頻率400-500之間哪一個訊號最強")
	private String content;

	@Schema(description = "是否是是非題", example = "true=1,false=0")
	private Long tofQuiz;

	@Schema(description = "是否是問答題", example = "true=1,false=0")
	private Long essayQuiz;

	@Schema(description = "答案", example = "450, 遵守按鈕, 1")
	private String answer;

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
