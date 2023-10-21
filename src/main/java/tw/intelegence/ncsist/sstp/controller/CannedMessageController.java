package tw.intelegence.ncsist.sstp.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.intelegence.ncsist.sstp.bean.CannedMessage;
import tw.intelegence.ncsist.sstp.service.CannedMessageService;

import java.time.LocalDate;
import java.util.List;


@RestController
@EnableAutoConfiguration
@RequestMapping("/cannedMessage")
@Tag(name = "8. CannedMessage api", description = "罐頭訊息")
public class CannedMessageController {

	@Autowired
	private CannedMessageService cannedMessageService;

	@Operation(summary = "初始化",description = "無")
	@GetMapping("/init")
	public String init(){
		String message = "已有內容列表。";

		if(cannedMessageService.getCannedMessageList().isEmpty()){
			Long longDate = System.currentTimeMillis();
			long newId = createId(0L);

			CannedMessage cannedMessage = new CannedMessage();
			cannedMessage.setCannedId(newId);
			cannedMessage.setMessageLevel(1001L);
			cannedMessage.setMessage("這是系統訊息");
			cannedMessage.setState(1L);
			cannedMessage.setLongDate(longDate);

			cannedMessageService.saveCannedMessage(cannedMessage);

			newId = createId(newId);
			cannedMessage.setCannedId(newId);
			cannedMessage.setMessageLevel(1002L);
			cannedMessage.setMessage("這是教官訊息");
			cannedMessage.setState(1L);
			cannedMessage.setLongDate(longDate);

			cannedMessageService.saveCannedMessage(cannedMessage);

			newId = createId(newId);
			cannedMessage.setCannedId(newId);
			cannedMessage.setMessageLevel(1003L);
			cannedMessage.setMessage("這是學生訊息");
			cannedMessage.setState(1L);
			cannedMessage.setLongDate(longDate);

			cannedMessageService.saveCannedMessage(cannedMessage);

			message = "初始化罐頭訊息完成。";
		}

		return message;
	}


	@Operation(summary = "取得新罐頭訊息ID", description = "無")
	@GetMapping("/getNewCannedMessageId")
	public ResponseEntity<Long> getNewCannedMessageId(){
		Long newCannedId = createId(cannedMessageService.getCannedId());

		return ResponseEntity.ok(newCannedId);
	}

	@Operation(summary = "取得所有罐頭訊息", description = "無")
	@GetMapping("/getAllCannedMessageList")
	public ResponseEntity<List<CannedMessage>> getAllCannedMessageList(HttpServletRequest request){

		List<CannedMessage> cannedMessageList = getCannedMessageList(request, 0L);

		return ResponseEntity.ok(cannedMessageList);
	}

	@Operation(summary = "取得指定罐頭訊息", description = "無")
	@GetMapping("/getCannedMessageListByMessageLevel/{messageLevel}")
	public ResponseEntity<List<CannedMessage>> getCannedMessageListByMessageLevel(HttpServletRequest request, @PathVariable Long messageLevel){

		List<CannedMessage> OperList = getCannedMessageList(request, messageLevel);

		return ResponseEntity.ok(OperList);
	}


	@Operation(summary = "新增罐頭訊息", description = "無")
	@PostMapping("/addCannedMessage")
	public ResponseEntity<List<CannedMessage>> addCannedMessage(HttpServletRequest request, @RequestBody CannedMessage cannedMessage){

		cannedMessageService.saveCannedMessage(cannedMessage);

		List<CannedMessage> cannedMessageList = getCannedMessageList(request, cannedMessage.getMessageLevel());

		return ResponseEntity.ok(cannedMessageList);
	}

	@Operation(summary = "更新罐頭訊息", description = "無")
	@PostMapping("/saveCannedMessage")
	public ResponseEntity<List<CannedMessage>> saveCannedMessage(HttpServletRequest request, @RequestBody CannedMessage cannedMessage){

		CannedMessage newCannedMessage = cannedMessageService.saveCannedMessage(cannedMessage);

		List<CannedMessage> cannedMessageList = getCannedMessageList(request, newCannedMessage.getMessageLevel());

		return ResponseEntity.ok(cannedMessageList);
	}

	@Operation(summary = "刪除罐頭訊息", description = "無")
	@DeleteMapping("/deleteCannedMessage/{id}/{cannedId}")
	public ResponseEntity<List<CannedMessage>> deleteCannedMessage(@PathVariable Long id, @PathVariable Long cannedId){

		return ResponseEntity.ok(cannedMessageService.deleteCannedMessage(id, cannedId));
	}

	//取得
	private List<CannedMessage> getCannedMessageList(HttpServletRequest request, Long messageLevel){
		HttpSession session = request.getSession();
//		String user = String.valueOf(session.getAttribute("user"));
		int level = Integer.parseInt(String.valueOf(session.getAttribute("level")));

		List<CannedMessage> cannedMessageList;
		if(messageLevel > 0){
			cannedMessageList = cannedMessageService.getCannedMessageListByMessageLevel(messageLevel);
		}else{
			cannedMessageList = cannedMessageService.getCannedMessageList();
		}

		return cannedMessageList;
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
