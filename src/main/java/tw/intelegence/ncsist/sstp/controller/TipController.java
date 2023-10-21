package tw.intelegence.ncsist.sstp.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.intelegence.ncsist.sstp.bean.Tip;
import tw.intelegence.ncsist.sstp.service.TipService;

import java.time.LocalDate;
import java.util.List;


@RestController
@EnableAutoConfiguration
@RequestMapping("/tip")
@Tag(name = "5. Tip api", description = "提示訊息")
public class TipController {

	@Autowired
	private TipService tipService;

	@Operation(summary = "初始化",description = "無")
	@GetMapping("/init")
	public String init(){

		String message = "已有提示列表。";

		if(tipService.getTipList().isEmpty()){
			long longDate = System.currentTimeMillis();

			long newId = createId(0L);

			Tip tip = new Tip();
			tip.setTipId(newId);
//			tip.setUnitId(newId);
//			tip.setContentId(newId);
			tip.setTitle("進行測驗");
			tip.setContent("須將測驗內容確實完成操作，然後答題，畫面中可能會有相關的操作指示");
			tip.setState(1L);
			tip.setLongDate(longDate);

			tipService.saveTip(tip);

			newId = createId(newId);

			tip.setTipId(newId);
//			tip.setUnitId(newId);
//			tip.setContentId(newId);
			tip.setTitle("信號搜索提示");
			tip.setContent("信號搜索提示敘述，畫面中可能會有相關的操作指示");
			tip.setState(1L);
			tip.setLongDate(longDate);

			tipService.saveTip(tip);

			message = "初始化提示列表完成";
		}


		return message;
	}


	@Operation(summary = "取得新提示ID", description = "無")
	@GetMapping("/getNewTipId")
	public ResponseEntity<Long> getNewTipId(){
		Long newTipId = createId(tipService.getTipId());

		return ResponseEntity.ok(newTipId);
	}

	@Operation(summary = "取得所有提示", description = "無")
	@GetMapping("/getAllTipList")
	public ResponseEntity<List<Tip>> getAllTipList(HttpServletRequest request){

		List<Tip> TipList = getTipList(request, 0L);

		return ResponseEntity.ok(TipList);
	}

	@Operation(summary = "取得指定提示", description = "無")
	@GetMapping("/getTipListByUnitId/{unitId}")
	public ResponseEntity<List<Tip>> getTipListByUnitId(HttpServletRequest request, @PathVariable Long unitId){

		List<Tip> TipList = getTipList(request, unitId);

		return ResponseEntity.ok(TipList);
	}


	@Operation(summary = "新增提示", description = "無")
	@PostMapping("/addTip")
	public ResponseEntity<List<Tip>> addTip(HttpServletRequest request, @RequestBody Tip Tip){

		tipService.saveTip(Tip);

		List<Tip> TipList = getTipList(request, Tip.getUnitId());

		return ResponseEntity.ok(TipList);
	}

	@Operation(summary = "更新提示", description = "無")
	@PostMapping("/saveTip")
	public ResponseEntity<List<Tip>> saveTip(HttpServletRequest request, @RequestBody Tip Tip){

		Tip newTip = tipService.saveTip(Tip);

		List<Tip> TipList = getTipList(request, newTip.getUnitId());

		return ResponseEntity.ok(TipList);
	}

	@Operation(summary = "刪除提示", description = "無")
	@DeleteMapping("/deleteTip/{id}/{unitId}")
	public ResponseEntity<List<Tip>> deleteTip(HttpServletRequest request, @PathVariable Long id, @PathVariable Long unitId){

		return ResponseEntity.ok(tipService.deleteTip(id, unitId));
	}

	//取得
	private List<Tip> getTipList(HttpServletRequest request, Long unitId){
		HttpSession session = request.getSession();
//		String user = String.valueOf(session.getAttribute("user"));
		int level = Integer.parseInt(String.valueOf(session.getAttribute("level")));

		List<Tip> TipList;
		if(unitId > 0){
			TipList = tipService.getTipListByUnitId(unitId);
		}else{
			TipList = tipService.getTipList();
		}

		return TipList;
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
