package tw.intelegence.ncsist.sstp.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.intelegence.ncsist.sstp.bean.Unit;
import tw.intelegence.ncsist.sstp.service.UnitService;

import java.time.LocalDate;
import java.util.List;


@RestController
@EnableAutoConfiguration
@RequestMapping("/unit")
@Tag(name = "3. Unit api", description = "單元")
public class UnitController {

	@Autowired
	private UnitService unitService;

	@Operation(summary = "初始化",description = "無")
	@GetMapping("/init")
	public String init(){

		String message = "已有單元列表。";

		if(unitService.getAllUnitList().isEmpty()){
			Long longDate = System.currentTimeMillis();
			long newId = createId(0L);

			Unit unit = new Unit();
			unit.setUnitId(newId);
			unit.setCourseId(202310006L);
			unit.setDescTitle1("測驗說明");
			unit.setDescContent1("參數說明測驗說明");
			unit.setDescTitle2("測驗情境");
			unit.setDescContent2("參數說明測驗情境");
			unit.setDescTitle3("測驗要點");
			unit.setDescContent3("參數說明測驗要點");
			unit.setUnitName("參數說明");
			unit.setUnitSubject("說明");
			unit.setUnitSchedule(30L);//30mins >>> 30 標準單位 分鐘
			unit.setVideoUrl("/path2");
			unit.setVideoFormat(".mp4");
//			unit.setPictureUrl1("");
//			unit.setPictureUrl2("");
//			unit.setPictureUrl3("");
//			unit.setPictureUrl4("");
//			unit.setContentList("");
			unit.setState(1L);
			unit.setLongDate(longDate);

			unitService.saveUnit(unit);


			newId = createId(newId);
			unit.setUnitId(newId);
			unit.setCourseId(202310006L);
			unit.setUnitName("信號搜索");
			unit.setDescTitle1("測驗說明");
			unit.setDescContent1("近期有不明無線電訊號出現在桃園機場附近，要求偵蒐車到場，找出不明無線電發射地點。");
			unit.setDescTitle2("測驗情境");
			unit.setDescContent2("將偵蒐車移動至桃園機場，搜索50~200mhz頻段內，-90db以上的可疑的定頻訊號。");
			unit.setDescTitle3("測驗要點");
			unit.setDescContent3("1．    使用搜索功能，找到50~200mhz頻段內可疑訊號\n" +
					"2．    使用監視功能，獲取可疑跳頻訊號\n" +
					"3．    報表判讀，確定可疑跳頻訊號");
			unit.setUnitSubject("設定");
			unit.setUnitSchedule(30L);//30mins >>> 30 標準單位 分鐘
			unit.setVideoUrl("Z:\\\\SSTP\\\\demo\\\\videos\\\\DEMO圖資畫面");
			unit.setVideoFormat(".mp4");
			unit.setPictureUrl1("C:\\\\SSTP\\\\demo\\\\images\\\\dfcs.jpg");
//			unit.setPictureUrl2("");
//			unit.setPictureUrl3("");
//			unit.setPictureUrl4("");
//			unit.setContentList("");
			unit.setState(1L);
			unit.setLongDate(longDate);
			unitService.saveUnit(unit);


			unit.setUnitId(5L);
			unit.setCourseId(5L);
			unit.setUnitName("信號搜索");
			unit.setDescTitle1("測驗說明");
			unit.setDescContent1("近期有不明無線電訊號出現在桃園機場附近，要求偵蒐車到場，找出不明無線電發射地點。");
			unit.setDescTitle2("測驗情境");
			unit.setDescContent2("將偵蒐車移動至桃園機場，搜索50~200mhz頻段內，-90db以上的可疑的定頻訊號。");
			unit.setDescTitle3("測驗要點");
			unit.setDescContent3("1．    使用搜索功能，找到50~200mhz頻段內可疑訊號\n" +
					"2．    使用監視功能，獲取可疑跳頻訊號\n" +
					"3．    報表判讀，確定可疑跳頻訊號");
			unit.setCreditUnits(6L);
			unit.setUnitOrder(4L);
			unit.setUnitSchedule(30L);//30mins >>> 30 標準單位 分鐘
			unit.setVideoUrl("Z:\\\\SSTP\\\\demo\\\\videos\\\\DEMO圖資畫面");
			unit.setVideoFormat(".mp4");
			unit.setPictureUrl1("C:\\\\SSTP\\\\demo\\\\images\\\\dfcs.jpg");
//			unit.setPictureUrl2("");
//			unit.setPictureUrl3("");
//			unit.setPictureUrl4("");
//			unit.setContentList("");
			unit.setState(1L);
			unit.setLongDate(longDate);
			unitService.saveUnit(unit);

			unit.setUnitId(8L);
			unit.setCourseId(6L);
			unit.setUnitName("信號搜索");
			unit.setDescTitle1("測驗說明");
			unit.setDescContent1("近期有不明無線電訊號出現在桃園機場附近，要求偵蒐車到場，找出不明無線電發射地點。");
			unit.setDescTitle2("測驗情境");
			unit.setDescContent2("將偵蒐車移動至桃園機場，搜索50~200mhz頻段內，-90db以上的可疑的定頻訊號。");
			unit.setDescTitle3("測驗要點");
			unit.setDescContent3("1．    使用搜索功能，找到50~200mhz頻段內可疑訊號\n" +
					"2．    使用監視功能，獲取可疑跳頻訊號\n" +
					"3．    報表判讀，確定可疑跳頻訊號");
			unit.setCreditUnits(6L);
			unit.setUnitOrder(4L);
			unit.setUnitSchedule(30L);//30mins >>> 30 標準單位 分鐘
			unit.setVideoUrl("Z:\\\\SSTP\\\\demo\\\\videos\\\\DEMO圖資畫面");
			unit.setVideoFormat(".mp4");
			unit.setPictureUrl1("C:\\\\SSTP\\\\demo\\\\images\\\\dfcs.jpg");
//			unit.setPictureUrl2("");
//			unit.setPictureUrl3("");
//			unit.setPictureUrl4("");
//			unit.setContentList("");
			unit.setState(1L);
			unit.setLongDate(longDate);
			unitService.saveUnit(unit);

			unit.setUnitId(6L);
			unit.setCourseId(5L);
			unit.setUnitName("信號追蹤(含監聽)");
			unit.setCreditUnits(6L);
			unit.setUnitOrder(4L);
			unit.setUnitSchedule(30L);//30mins >>> 30 標準單位 分鐘
			unit.setState(1L);
			unit.setLongDate(longDate);
			unitService.saveUnit(unit);

			unit.setUnitId(7L);
			unit.setCourseId(5L);
			unit.setUnitName("信號監視");
			unit.setCreditUnits(6L);
			unit.setUnitOrder(4L);
			unit.setUnitSchedule(30L);//30mins >>> 30 標準單位 分鐘
			unit.setState(1L);
			unit.setLongDate(longDate);
			unitService.saveUnit(unit);

			unit.setUnitId(4L);
			unit.setCourseId(5L);
			unit.setUnitName("參數說明");
			unit.setCreditUnits(6L);
			unit.setUnitOrder(4L);
			unit.setUnitSchedule(30L);//30mins >>> 30 標準單位 分鐘
			unit.setState(1L);
			unit.setLongDate(longDate);
			unitService.saveUnit(unit);

			unit.setUnitId(39L);
			unit.setCourseId(39L);
			unit.setUnitName("團體連線課程");
			unit.setDescTitle1("測驗說明");
			unit.setDescContent1("這支影片用來介紹偵蒐測項軟體-信號偵蒐測向課程 - 信號搜索單元。信號搜索是偵蒐車進到新環境的第一個動作，快速且大範圍蒐集周遭環境的定頻訊號，了解周遭的訊號環境，才能進一步判斷訊息重要性的優先順序，進行追蹤、信號監聽、跳頻信號源判斷。藉此掌握敵人之通訊或傳輸資料，並藉此獲取情報資料，掌握敵人的動向，作為戰鬥指導的依據。");
			unit.setDescTitle2("測驗情境");
			unit.setDescContent2("現代化戰爭中，天空充斥著無數的電子訊號，例如：廣播訊號，電視訊號以及各種無線電訊號，因此在進行信號搜索時，為了避免獲得的資料過於龐雜，需要針對搜索目標，設定限縮條件 ，並依照條件使用剔除功能， 讓搜索結果更加貼近目標，以便進行後續追踨、監視或干擾…等行動。");
			unit.setDescTitle3("測驗要點");
			unit.setDescContent3("本次模擬情境中，指揮部接到情報資訊，發現基隆外海疑似有敵方無人機出沒，派遣一台偵蒐車到場，搜索出無人機信號，以驗證情報真偽。\n" +
					"\n" +
					"1.\t使用搜索功能，找到400~500MHz頻段，「頻率間隔」12.5，「截收信號強度」-70db以上可疑定頻訊號\n" +
					"2.\t剔除商用無人機433 MHz波道訊號\n" +
					"3.\t剔除480～490MHz波段訊號 \n" +
					"4.\t剔除100～270度來向角訊號\n");
			unit.setCreditUnits(6L);
			unit.setUnitOrder(4L);
			unit.setUnitSchedule(30L);//30mins >>> 30 標準單位 分鐘
			unit.setVideoUrl("Z:\\\\SSTP\\\\demo\\\\videos\\\\DEMO圖資畫面");
			unit.setVideoFormat(".mp4");
			unit.setPictureUrl1("C:\\\\SSTP\\\\demo\\\\images\\\\dfcs.jpg");
//			unit.setPictureUrl2("");
//			unit.setPictureUrl3("");
//			unit.setPictureUrl4("");
//			unit.setContentList("");
			unit.setState(1L);
			unit.setLongDate(longDate);
			unitService.saveUnit(unit);

			unit.setUnitId(40L);
			unit.setCourseId(6L);
			unit.setUnitName("團體連線課程");
			unit.setDescTitle1("測驗說明");
			unit.setDescContent1("這支影片用來介紹偵蒐測項軟體-信號偵蒐測向課程 - 信號搜索單元。信號搜索是偵蒐車進到新環境的第一個動作，快速且大範圍蒐集周遭環境的定頻訊號，了解周遭的訊號環境，才能進一步判斷訊息重要性的優先順序，進行追蹤、信號監聽、跳頻信號源判斷。藉此掌握敵人之通訊或傳輸資料，並藉此獲取情報資料，掌握敵人的動向，作為戰鬥指導的依據。");
			unit.setDescTitle2("測驗情境");
			unit.setDescContent2("現代化戰爭中，天空充斥著無數的電子訊號，例如：廣播訊號，電視訊號以及各種無線電訊號，因此在進行信號搜索時，為了避免獲得的資料過於龐雜，需要針對搜索目標，設定限縮條件 ，並依照條件使用剔除功能， 讓搜索結果更加貼近目標，以便進行後續追踨、監視或干擾…等行動。");
			unit.setDescTitle3("測驗要點");
			unit.setDescContent3("本次模擬情境中，指揮部接到情報資訊，發現基隆外海疑似有敵方無人機出沒，派遣一台偵蒐車到場，搜索出無人機信號，以驗證情報真偽。\n" +
					"\n" +
					"1.\t使用搜索功能，找到400~500MHz頻段，「頻率間隔」12.5，「截收信號強度」-70db以上可疑定頻訊號\n" +
					"2.\t剔除商用無人機433 MHz波道訊號\n" +
					"3.\t剔除480～490MHz波段訊號 \n" +
					"4.\t剔除100～270度來向角訊號\n");
			unit.setCreditUnits(6L);
			unit.setUnitOrder(4L);
			unit.setUnitSchedule(30L);//30mins >>> 30 標準單位 分鐘
			unit.setVideoUrl("Z:\\\\SSTP\\\\demo\\\\videos\\\\DEMO圖資畫面");
			unit.setVideoFormat(".mp4");
			unit.setPictureUrl1("C:\\\\SSTP\\\\demo\\\\images\\\\dfcs.jpg");
//			unit.setPictureUrl2("");
//			unit.setPictureUrl3("");
//			unit.setPictureUrl4("");
//			unit.setContentList("");
			unit.setState(1L);
			unit.setLongDate(longDate);
			unitService.saveUnit(unit);


			message = "初始化單元完成。";
		}

		return message;


	}


	@Operation(summary = "取得unitId單元", description = "無")
	@GetMapping("/getUnitId")
	public ResponseEntity<Long> getUnitId(){
		return ResponseEntity.ok(unitService.getUnitId());
	}

	@Operation(summary = "用unitId取得所有單元", description = "無")
	@GetMapping("/getAllUnitByUnitId/{unitId}")
	public ResponseEntity<List<Unit>> getAllUnitByUnitId(HttpServletRequest request, @PathVariable Long unitId){

		List<Unit> unitList = getUnitList(request, unitId);

		return ResponseEntity.ok(unitList);
	}

	@Operation(summary = "取得所有單元", description = "無")
	@GetMapping("/getAllUnit")
	public ResponseEntity<List<Unit>> getAllUnit(HttpServletRequest request){

		List<Unit> unitList = getUnitList(request, 0L);

		return ResponseEntity.ok(unitList);
	}


	@Operation(summary = "新增單元", description = "無")
	@PostMapping("/addUnit")
	public ResponseEntity<List<Unit>> addUnit(HttpServletRequest request, @RequestBody Unit unit){


		Unit newUnit = unitService.saveUnit(unit);

		List<Unit> unitList = getUnitList(request, newUnit.getCourseId());

		return ResponseEntity.ok(unitList);
	}


	@Operation(summary = "更新單元", description = "無")
	@PostMapping("/updateUnit")
	public ResponseEntity<List<Unit>> updateUnit(HttpServletRequest request, @RequestBody Unit unit){

		Unit newUnit = unitService.saveUnit(unit);

		List<Unit> unitList = getUnitList(request, newUnit.getCourseId());

		return ResponseEntity.ok(unitList);
	}

	@Operation(summary = "刪除單元", description = "無")
	@PostMapping("/deleteUnit")
	public ResponseEntity<List<Unit>> deleteUnit(@RequestBody Unit unit){

		return ResponseEntity.ok(unitService.deleteUnit(unit));
	}


	//取得
	private List<Unit> getUnitList(HttpServletRequest request, Long courseId){
		HttpSession session = request.getSession();
//		String user = String.valueOf(session.getAttribute("user"));
		int level = Integer.parseInt(String.valueOf(session.getAttribute("level")));

		List<Unit> unitList;

		if(courseId > 0){
			unitList = unitService.getUnitListByCourseId(courseId);
		}else{
			unitList = unitService.getAllUnitList();
		}

		return unitList;
	}

	private long createId(long id){
		LocalDate localDate = LocalDate.now();
		long newId = localDate.getYear() * 1000;

		if(id > 1 && newId < id){
			newId = ++id;
		}else{
			newId++;
		}

		return newId;
	}

}
