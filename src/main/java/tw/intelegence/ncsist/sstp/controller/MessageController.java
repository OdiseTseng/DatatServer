package tw.intelegence.ncsist.sstp.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.intelegence.ncsist.sstp.bean.Message;
import tw.intelegence.ncsist.sstp.service.MessageService;
import tw.intelegence.ncsist.sstp.session.SessionContext;

import java.time.LocalDate;
import java.util.Enumeration;
import java.util.List;


@RestController
@EnableAutoConfiguration
@RequestMapping("/message")
@Tag(name = "8. CannedMessage api", description = "罐頭訊息")
public class MessageController {

	@Autowired
	private MessageService messageService;

	@Operation(summary = "初始化",description = "無")
	@GetMapping("/init")
	public String init(){
		String message = "已有內容列表。";

		if(messageService.getCannedMessageList().isEmpty()){
			Long longDate = System.currentTimeMillis();
			long newId = createId(0L);

			Message cannedMessage = new Message();
			cannedMessage.setCannedId(newId);
			cannedMessage.setMessageLevel(1001L);
			cannedMessage.setMessage("這是系統訊息");
			cannedMessage.setState(1L);
			cannedMessage.setLongDate(longDate);

			messageService.saveCannedMessage(cannedMessage);

			newId = createId(newId);
			cannedMessage.setCannedId(newId);
			cannedMessage.setMessageLevel(1002L);
			cannedMessage.setMessage("這是教官訊息");
			cannedMessage.setState(1L);
			cannedMessage.setLongDate(longDate);

			messageService.saveCannedMessage(cannedMessage);

			newId = createId(newId);
			cannedMessage.setCannedId(newId);
			cannedMessage.setMessageLevel(1003L);
			cannedMessage.setMessage("這是學生訊息");
			cannedMessage.setState(1L);
			cannedMessage.setLongDate(longDate);

			messageService.saveCannedMessage(cannedMessage);

			message = "初始化罐頭訊息完成。";
		}

		return message;
	}


	@Operation(summary = "取得新罐頭訊息ID", description = "無")
	@GetMapping("/getNewCannedMessageId")
	public ResponseEntity<Long> getNewCannedMessageId(){
		Long newCannedId = createId(messageService.getCannedId());

		return ResponseEntity.ok(newCannedId);
	}

	@Operation(summary = "取得所有罐頭訊息", description = "無")
	@GetMapping("/getAllCannedMessageList")
	public ResponseEntity<List<Message>> getAllCannedMessageList(HttpServletRequest request){

		List<Message> messageList = getCannedMessageList(request, 0L);

		return ResponseEntity.ok(messageList);
	}

	@Operation(summary = "取得指定罐頭訊息", description = "無")
	@GetMapping("/getCannedMessageListByMessageLevel/{messageLevel}")
	public ResponseEntity<List<Message>> getCannedMessageListByMessageLevel(HttpServletRequest request, @PathVariable Long messageLevel){

		List<Message> OperList = getCannedMessageList(request, messageLevel);

		return ResponseEntity.ok(OperList);
	}


	@Operation(summary = "新增罐頭訊息", description = "無")
	@PostMapping("/addCannedMessage")
	public ResponseEntity<List<Message>> addCannedMessage(HttpServletRequest request, @RequestBody Message message){

		messageService.saveCannedMessage(message);

		List<Message> messageList = getCannedMessageList(request, message.getMessageLevel());

		return ResponseEntity.ok(messageList);
	}

	@Operation(summary = "更新罐頭訊息", description = "無")
	@PostMapping("/saveCannedMessage")
	public ResponseEntity<List<Message>> saveCannedMessage(HttpServletRequest request, @RequestBody Message message){

		Message newMessage = messageService.saveCannedMessage(message);

		List<Message> messageList = getCannedMessageList(request, newMessage.getMessageLevel());

		return ResponseEntity.ok(messageList);
	}

	@Operation(summary = "刪除罐頭訊息", description = "無")
	@DeleteMapping("/deleteCannedMessage/{id}/{cannedId}")
	public ResponseEntity<List<Message>> deleteCannedMessage(@PathVariable Long id, @PathVariable Long cannedId){

		return ResponseEntity.ok(messageService.deleteCannedMessage(id, cannedId));
	}

	//取得
	private List<Message> getCannedMessageList(HttpServletRequest request, Long messageLevel){
//		HttpSession session = request.getSession();
//		String user = String.valueOf(session.getAttribute("user"));
		String sessionId = request.getRequestedSessionId();
		System.out.println("sessionId : " + sessionId);

		sessionId = sessionId.split("=")[1];

		System.out.println("sessionId : " + sessionId);

		SessionContext sessionContext = SessionContext.getInstance();
		HttpSession session = sessionContext.getSession(sessionId);

		Enumeration<String> attributeNames = session.getAttributeNames();

		while (attributeNames.hasMoreElements()) {
			String attributeName = attributeNames.nextElement();
			Object attributeValue = session.getAttribute(attributeName);
			System.out.println("attributeName : " + attributeName + " ; attributeValue : " + attributeValue);
		}

		int level = Integer.parseInt(String.valueOf(session.getAttribute("level")));

		List<Message> messageList;
		if(messageLevel > 0){
			messageList = messageService.getCannedMessageListByMessageLevel(messageLevel);
		}else{
			messageList = messageService.getCannedMessageList();
		}

		return messageList;
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
