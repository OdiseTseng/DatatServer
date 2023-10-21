package tw.intelegence.ncsist.sstp.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class UnitDTO implements Serializable {


	private String unitName;

	private String unitSchedule;

	private String unitSubject;

	private Long unitOrder;

	private String descTitle1;

	private String descContent1;

	private String descTitle2;

	private String descContent2;

	private String descTitle3;

	private String descContent3;

	private String videoUrl;

	private String videoFormat;

	private String videoContents;

	private String dfcsFilename;

	private Long creditUnits;

	private String pictureUrl1;

	private String pictureUrl2;

	private String pictureUrl3;

	private String pictureUrl4;

	private String contentList;

	private Long state;

	private Long longDate;
}
