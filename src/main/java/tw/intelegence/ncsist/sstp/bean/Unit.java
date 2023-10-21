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
@Table(name = "unit")
@Schema(name = "課程單元", description = "課程單元資料")
public class Unit implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Primary Key")
	private Long id;

	@Schema(description = "課程編號", example = "202301001")
	private Long courseId;

	@Schema(description = "單元編號", example = "0000")
	private Long unitId;

	@Schema(description = "單元名稱", example = "開啟教學軟體")
	private String unitName;

	@Schema(description = "單元時間", example = "5 = 5分鐘")
	private Long unitSchedule;

	@Schema(description = "單元主題", example = "教學軟體的啟動步驟")
	private String unitSubject;

	@Schema(description = "單元排序", example = "001001001")
	private Long unitOrder;

	@Schema(description = "描述標題1", example = "單元目標")
	private String descTitle1;

	@Schema(description = "描述內容1", example = "學會啟動教學軟體")
	private String descContent1;

	@Schema(description = "描述標題2", example = "")
	private String descTitle2;

	@Schema(description = "描述內容2", example = "")
	private String descContent2;

	@Schema(description = "描述標題3", example = "")
	private String descTitle3;

	@Schema(description = "描述內容3", example = "")
	private String descContent3;

	@Schema(description = "影片網址", example = "http://192.168.0.1/example.mp4")
	private String videoUrl;

	@Schema(description = "影片格式", example = "video/mp4")
	private String videoFormat;

	@Schema(description = "影片段落", example = "01,02,03,04(分鐘)")
	private String videoContents;

	@Schema(description = "DFCS檔案名稱", example = "video/mp4")
	private String dfcsFilename;

	@Schema(description = "單元學分數", example = "001")
	private Long creditUnits;

	@Schema(description = "圖片網址1", example = "http://192.168.0.1/example.png")
	private String pictureUrl1;

	@Schema(description = "圖片網址2", example = "http://192.168.0.1/example.png")
	private String pictureUrl2;

	@Schema(description = "圖片網址3", example = "http://192.168.0.1/example.png")
	private String pictureUrl3;

	@Schema(description = "圖片網址4", example = "http://192.168.0.1/example.png")
	private String pictureUrl4;

	@Schema(description = "內容列表", example = "頻率設定、準位設定的ID")
	private String contentList;

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
