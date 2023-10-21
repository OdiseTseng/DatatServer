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
			unit.setDescTitle1("測驗說明");
			unit.setDescContent1("近期有不明無線電訊號出現在桃園機場附近，要求偵蒐車到場，找出不明無線電發射地點。");
			unit.setDescTitle2("測驗情境");
			unit.setDescContent2("將偵蒐車移動至桃園機場，搜索50~200mhz頻段內，-90db以上的可疑的定頻訊號。");
			unit.setDescTitle3("測驗要點");
			unit.setDescContent3("1．    使用搜索功能，找到50~200mhz頻段內可疑訊號\n" +
					"2．    使用監視功能，獲取可疑跳頻訊號\n" +
					"3．    報表判讀，確定可疑跳頻訊號");
			unit.setUnitName("信號搜索");
			unit.setUnitSubject("設定");
			unit.setUnitSchedule(30L);//30mins >>> 30 標準單位 分鐘
			unit.setVideoUrl("Z:\\\\SSTP\\\\demo\\\\videos\\\\DEMO圖資畫面");
			unit.setVideoFormat(".mp4");
//			unit.setPictureUrl1("");
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
