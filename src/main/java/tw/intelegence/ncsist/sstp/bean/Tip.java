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
@Table(name = "tip")
@Schema(name = "提示", description = "補充說明的部分")
public class Tip implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Primary Key")
	private Long id;

	@Schema(description = "提示編號", example = "001001001")
	private Long tipId;

	@Schema(description = "單元編號", example = "001001")
	private Long unitId;

	@Schema(description = "內容編號", example = "001001")
	private Long contentId;

	@Schema(description = "提示標題", example = "信號強度")
	private String title;

	@Schema(description = "提示內容", example = "從-100db開始起算")
	private String content;

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
