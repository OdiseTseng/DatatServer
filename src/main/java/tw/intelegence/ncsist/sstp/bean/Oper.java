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
@Table(name = "oper")
@Schema(name = "內容", description = "測驗的內容描述")
public class Oper implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Primary Key")
	private Long id;

	@Schema(description = "oper編號", example = "001001")
	private Long operId;

	@Schema(description = "單元編號", example = "001001")
	private Long unitId;

	@Schema(description = "內容編號", example = "001001001")
	private Long contentId;

	@Schema(description = "題目", example = "subject1")
	private String title;

	@Schema(description = "題目(中文)", example = "題目1")
	private String titleCh;

	@Schema(description = "圖片名稱", example = "圖片名稱")
	private String pictureName;

	@Schema(description = "答案", example = "001, 搜索訊號...這類")
	private String answer;

	@Schema(description = "隊伍", example = "1、2、3、4、....")
	private Long team;

	@Schema(description = "席位", example = "3 = 車長、 2 = 通訊兵 、 3 = 操作手 ")
	private Long role;

	@Schema(description = "分組1", example = "1001001")
	private Long group1;

	@Schema(description = "分組2", example = "1001001")
	private Long group2;

	@Schema(description = "分組3", example = "1001001")
	private Long group3;

	@Schema(description = "分組4", example = "1001001")
	private Long group4;

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
