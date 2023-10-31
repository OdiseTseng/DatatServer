package tw.intelegence.ncsist.sstp.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.intelegence.ncsist.sstp.bean.Oper;
import tw.intelegence.ncsist.sstp.bean.Quiz;
import tw.intelegence.ncsist.sstp.service.OperService;

import java.time.LocalDate;
import java.util.List;


@RestController
@EnableAutoConfiguration
@RequestMapping("/oper")
@Tag(name = "6. Operations api", description = "Sikulix 操作用")
public class OperController {

	@Autowired
	private OperService operService;

	@Operation(summary = "初始化",description = "無")
	@GetMapping("/init")
	public String init(){

		String message = "已有操作列表。";

		if(operService.getOperList().isEmpty()){
			long longDate = System.currentTimeMillis();

			long newId = createId(0L);
			long quizId = 0L;

			Oper operation = new Oper();
			operation.setUnitId(5L);
			operation.setAnswer("400");
			operation.setState(1L);
			operation.setLongDate(longDate);
			operation.setOperId(1L);
			operation.setContentId(1L);
			operService.saveOper(operation);

			operation.setUnitId(5L);
			operation.setAnswer("500");
			operation.setState(1L);
			operation.setOperId(2L);
			operation.setContentId(2L);
			operation.setLongDate(longDate);

			operService.saveOper(operation);

			operation.setUnitId(5L);
			operation.setAnswer("-70");
			operation.setState(1L);
			operation.setOperId(3L);
			operation.setContentId(3L);
			operation.setLongDate(longDate);

			operService.saveOper(operation);

			operation.setUnitId(5L);
			operation.setAnswer("100");
			operation.setState(1L);
			operation.setOperId(4L);
			operation.setContentId(4L);
			operation.setLongDate(longDate);

			operService.saveOper(operation);

			operation.setUnitId(5L);
			operation.setAnswer("270");
			operation.setState(1L);
			operation.setOperId(5L);
			operation.setContentId(5L);
			operation.setLongDate(longDate);

			operService.saveOper(operation);

			operation.setUnitId(39L);
			operation.setAnswer("400");
			operation.setState(1L);
			operation.setOperId(6L);
			operation.setContentId(6L);
			operation.setLongDate(longDate);

			operService.saveOper(operation);

			operation.setUnitId(39L);
			operation.setAnswer("500");
			operation.setState(1L);
			operation.setOperId(7L);
			operation.setContentId(7L);
			operation.setLongDate(longDate);

			operService.saveOper(operation);

			operation.setUnitId(39L);
			operation.setAnswer("-70");
			operation.setState(1L);
			operation.setOperId(8L);
			operation.setContentId(8L);
			operation.setLongDate(longDate);

			operService.saveOper(operation);

			operation.setUnitId(39L);
			operation.setAnswer("100");
			operation.setState(1L);
			operation.setOperId(9L);
			operation.setContentId(9L);
			operation.setLongDate(longDate);

			operService.saveOper(operation);

			operation.setUnitId(39L);
			operation.setAnswer("270");
			operation.setState(1L);
			operation.setOperId(10L);
			operation.setContentId(10L);
			operation.setLongDate(longDate);

			operService.saveOper(operation);


			message = "初始化操作列表完成";
		}


		return message;
	}


	@Operation(summary = "取得新操作ID", description = "無")
	@GetMapping("/getNewOperId")
	public ResponseEntity<Long> getNewOperId(){
		Long newOperId = createId(operService.getOperId());

		return ResponseEntity.ok(newOperId);
	}

	@Operation(summary = "取得所有操作", description = "無")
	@GetMapping("/getAllOperList")
	public ResponseEntity<List<Oper>> getAllOperList(HttpServletRequest request){

		List<Oper> OperList = getOperList(request, 0L);

		return ResponseEntity.ok(OperList);
	}

	@Operation(summary = "取得指定操作", description = "無")
	@GetMapping("/getOperListByUnitId/{unitId}")
	public ResponseEntity<List<Oper>> getOperListByUnitId(HttpServletRequest request, @PathVariable Long unitId){

		List<Oper> OperList = getOperList(request, unitId);

		return ResponseEntity.ok(OperList);
	}


	@Operation(summary = "新增操作", description = "無")
	@PostMapping("/addOper")
	public ResponseEntity<List<Oper>> addOper(HttpServletRequest request, @RequestBody Oper Oper){

		operService.saveOper(Oper);

		List<Oper> OperList = getOperList(request, Oper.getUnitId());

		return ResponseEntity.ok(OperList);
	}

	@Operation(summary = "更新操作", description = "無")
	@PostMapping("/saveOper")
	public ResponseEntity<List<Oper>> saveOper(HttpServletRequest request, @RequestBody Oper Oper){

		Oper newOper = operService.saveOper(Oper);

		List<Oper> OperList = getOperList(request, newOper.getUnitId());

		return ResponseEntity.ok(OperList);
	}

	@Operation(summary = "刪除操作", description = "無")
	@DeleteMapping("/deleteOper/{id}/{unitId}")
	public ResponseEntity<List<Oper>> deleteOper(@PathVariable Long id, @PathVariable Long unitId){

		return ResponseEntity.ok(operService.deleteOper(id, unitId));
	}

	//取得
	private List<Oper> getOperList(HttpServletRequest request, Long unitId){
		HttpSession session = request.getSession();
//		String user = String.valueOf(session.getAttribute("user"));
//		int level = Integer.parseInt(String.valueOf(session.getAttribute("level")));

		List<Oper> OperList;
		if(unitId > 0){
			OperList = operService.getOperListByUnitId(unitId);
		}else{
			OperList = operService.getOperList();
		}

		return OperList;
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
