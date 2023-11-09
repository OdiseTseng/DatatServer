package tw.intelegence.ncsist.sstp.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import tw.intelegence.ncsist.sstp.bean.User;
import tw.intelegence.ncsist.sstp.model.MsgDTO;
import tw.intelegence.ncsist.sstp.model.NettyDTO;
import tw.intelegence.ncsist.sstp.model.UserDTO;
import tw.intelegence.ncsist.sstp.netty.controller.NettyMsgController;
import tw.intelegence.ncsist.sstp.service.UserService;
import tw.intelegence.ncsist.sstp.utils.func.SHAEncoder;
import tw.intelegence.ncsist.sstp.utils.text.NettyCode;
import tw.intelegence.ncsist.sstp.utils.text.ServerCode;

import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;


@RestController
@EnableAutoConfiguration
@RequestMapping("/user")
@Tag(name = "1. User api", description = "使用者")
public class UserController {

	@Autowired
	private UserService userService;

	private NettyMsgController nettyMsgController;


	@Operation(operationId = "1",summary = "1. 初始化",description = "無")
	@GetMapping("/init")
	public String init(){

		String message = "已初始化，略過。";

		if(userService.getByUsernameAndPassword("1", SHAEncoder.getSHA256("1")) == null){
			long longDate = System.currentTimeMillis();

			//教官
			User user = new User();
			user.setUsername("1");
			user.setPassword(SHAEncoder.getSHA256("1"));
			user.setName("諸葛教官");
			user.setLevel(1002L);
			user.setGrade(2004L);
			user.setStudentId(2075001L);
			user.setStudentBatch(2023001L);
			user.setStudentWork("教官");
			user.setStudentUnit("教育訓練部教官");
			user.setStudentUnitCode(996);
			user.setIp("192.168.50.46");
			user.setState(1L);
			user.setLongDate(longDate);
//		userService.save(user);
			userService.updateUser(user);

			//指揮
			user.setUsername("2");
			user.setPassword(SHAEncoder.getSHA256("2"));
			user.setName("劉禪指揮");
			user.setLevel(1003L);
			user.setGrade(2005L);
			user.setStudentId(2075002L);
			user.setStudentBatch(2023001L);
			user.setStudentWork("指揮官");
			user.setStudentUnit("情資收集作戰部");
			user.setStudentUnitCode(995);
			user.setIp("192.168.50.47");
			user.setState(1L);
			user.setLongDate(longDate);
			userService.updateUser(user);

			//學生
			user.setUsername("3");
			user.setPassword(SHAEncoder.getSHA256("3"));
			user.setName("馬超學生");
			user.setLevel(1004L);
			user.setGrade(2008L);
			user.setStudentId(2075003L);
			user.setStudentBatch(2023001L);
			user.setStudentWork("作戰官");
			user.setStudentUnit("情資作戰部");
			user.setStudentUnitCode(990);
			user.setIp("192.168.50.48");
			user.setState(1L);
			user.setLongDate(longDate);
			userService.updateUser(user);

			//學生
			user.setUsername("4");
			user.setPassword(SHAEncoder.getSHA256("4"));
			user.setName("馬良學生");
			user.setLevel(1004L);
			user.setGrade(2009L);
			user.setStudentId(2075004L);
			user.setStudentBatch(2023001L);
			user.setStudentWork("作戰官");
			user.setStudentUnit("情資作戰部");
			user.setStudentUnitCode(990);
			user.setIp("192.168.50.49");
			user.setState(1L);
			user.setLongDate(longDate);
			userService.updateUser(user);

			//學生
			user.setUsername("5");
			user.setPassword(SHAEncoder.getSHA256("5"));
			user.setName("阿諾州長");
			user.setLevel(1004L);
			user.setGrade(2010L);
			user.setStudentId(2075005L);
			user.setStudentBatch(2023001L);
			user.setStudentWork("作戰官");
			user.setStudentUnit("情資作戰部");
			user.setStudentUnitCode(990);
			user.setIp("192.168.50.50");
			user.setState(1L);
			user.setLongDate(longDate);
			userService.updateUser(user);

			//命名規則稍微不太一樣，我先以不影響我這邊TS系統為主設立DB，server一樣先用我那邊建立的
			//教官1
			user.setUsername("test@1");
			user.setPassword(SHAEncoder.getSHA256("test@01"));
			user.setName("諸葛孔明");
			user.setLevel(2L);
			user.setGrade(0L);
			user.setStudentId(50001L);
			user.setStudentBatch(0L);
			user.setStudentWork("教官");
			user.setStudentUnit("教育訓練部教官");
			user.setState(1L);
			user.setLongDate(longDate);
			userService.updateUser(user);
			//號手1
			user.setUsername("test@10");
			user.setPassword(SHAEncoder.getSHA256("test@010"));
			user.setName("杰倫Chou");
			user.setLevel(1L);
			user.setGrade(0L);
			user.setStudentId(70010L);
			user.setStudentBatch(0L);
			user.setStudentWork("作戰官");
			user.setStudentUnit("中區");
			user.setState(1L);
			user.setLongDate(longDate);
			userService.updateUser(user);
			//號手2
			user.setUsername("test@20");
			user.setPassword(SHAEncoder.getSHA256("test@020"));
			user.setName("Jacky");
			user.setLevel(1L);
			user.setGrade(1L);
			user.setStudentId(70040L);
			user.setStudentBatch(0L);
			user.setStudentWork("作戰官");
			user.setStudentUnit("北區");
			user.setState(1L);
			user.setLongDate(longDate);
			userService.updateUser(user);

			message = "初始化使用者完成。";
		}

		return message;
	}

	@Operation(operationId = "2", summary = "2. 登入", description = "login")
//	@ApiResponse(responseCode = "200", description = "Login successful")
//	@ApiResponse(responseCode = "401", description = "Unauthorized")
	@PostMapping("/login")
//	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//	public String doLogin(HttpServletRequest request, @RequestBody(description = "Map containing username and password", required = true,
//			content = @Content(schema = @Schema(implementation = Map.class))) Map<String, String> map){

	public ResponseEntity<?> doLogin(HttpServletRequest request, @RequestBody UserDTO userDTO){

		System.out.println("userDTO: " + userDTO.toString());
		String username = userDTO.getUsername();
		String password = userDTO.getPassword();

		System.out.println("doLogin username: " + username);
		System.out.println("doLogin password: " + password);

//		LogDataHelper.writeLog("Route checkLogin & checkUsername username : " + username + " ;;; password :" + password);
//		LogDataHelper.writeLog("clientIP", request.getRemoteAddr());

		User user = userService.getByUsernameAndPassword( username, password );
		if(user != null){

			System.out.println("user : " + user.getUsername());
			System.out.println("level : " + user.getLevel());

			MsgDTO msgDTO = new MsgDTO();
			msgDTO.setCmd(NettyCode.CMD_LOGIN);
			msgDTO.setFrom(user.getName());

			NettyDTO nettyDTO = new NettyDTO();
			nettyDTO.setUsername(user.getUsername());
			nettyDTO.setName(user.getName());
			nettyDTO.setLevel(user.getLevel());
			nettyDTO.setGrade(user.getGrade());
			nettyDTO.setStudentUnit(user.getStudentUnit());
			nettyDTO.setIp(user.getIp());
			nettyDTO.setStudentId(user.getStudentId());
			nettyDTO.setStudentBatch(user.getStudentBatch());

			String sourceIp = request.getRemoteAddr();
			System.out.println("ip : " + sourceIp);
//			sourceIp = sourceIp.split(":")[0];
//			sourceIp = "/" + sourceIp;
			System.out.println("new ip : " + sourceIp);

			HttpSession session = request.getSession();
			session.setAttribute("user", user.getUsername());
			session.setAttribute( "level", user.getLevel());

			Enumeration<String> attributeNames = session.getAttributeNames();

			while (attributeNames.hasMoreElements()) {
				String attributeName = attributeNames.nextElement();
				Object attributeValue = session.getAttribute(attributeName);
				System.out.println("attributeName : " + attributeName + " ; attributeValue : " + attributeValue);
			}

			String sourceId = userDTO.getCtxId();
			System.out.println("sourceId : " + sourceId);

//			ConnectController.setIdIpNettyDTO_Demo(sourceId, sourceIp, nettyDTO);
//			NettyService.sendClientsMsg_Demo(sourceId, sourceIp, msgDTO);

			//for demo
//			new NettyMsgController().setIdNettyDTO(sourceId + "-" + sourceIp, nettyDTO, msgDTO);

			if(nettyMsgController == null){
				nettyMsgController = new NettyMsgController();
			}
			nettyMsgController.setIdNettyDTO(sourceId, nettyDTO, msgDTO);

			//			NettyService.sendClientsMsg(sourceId + "-" + sourceIp, msgDTO);

//			return CommonFunction.setResponse( ServerCode.INFO_LOGIN_SUCCESS , nettyDTO.toString());
			return ResponseEntity.ok(nettyDTO);
		}else{
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ServerCode.INFO_LOGIN_FAIL);
		}
	}

	@Operation(operationId = "3", summary = "取得所有使用者列表", description = "get user list")
	@GetMapping("/getAllUserList")
	public List<User> getAllUserList(){
		return userService.getAllUsers();
	}

	@Operation(operationId = "4", summary = "更新使用者", description = "update user")
	@PostMapping("/updateUser")
	public User updateUser(@RequestBody User user){
		return userService.updateUser(user);
	}

	@Operation(operationId = "5", summary = "刪除使用者", description = "delete user")
	@PostMapping("/deleteUser")
	public Long deleteUser(@RequestBody User user){
		return userService.deleteUser(user);
	}

	@GetMapping("/logout")
	public ResponseEntity<String> logout(HttpSession session) {
		session.invalidate();
		return ResponseEntity.ok("登出成功");
	}
	@GetMapping("/online-controller")
	public CompletableFuture<ResponseEntity<String>> onlineEndpoint() {
		CompletableFuture<String> result = simulateProcessingAsync();
		return result.thenApply(scoreData -> ResponseEntity.ok(scoreData));
	}

	@Async
	public CompletableFuture<String> simulateProcessingAsync() {
		try {
			System.out.println("收到訊息開始倒數");
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		System.out.println("Sending 200 OK response.");
		return CompletableFuture.completedFuture("Request received. Processing...");
	}
}
