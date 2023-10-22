package tw.intelegence.ncsist.sstp.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import tw.intelegence.ncsist.sstp.bean.User;
import tw.intelegence.ncsist.sstp.model.MsgDTO;
import tw.intelegence.ncsist.sstp.model.NettyDTO;
import tw.intelegence.ncsist.sstp.model.UserDTO;
import tw.intelegence.ncsist.sstp.netty.controller.ConnectController;
import tw.intelegence.ncsist.sstp.service.UserService;
import tw.intelegence.ncsist.sstp.utils.func.CommonFunction;
import tw.intelegence.ncsist.sstp.utils.func.SHAEncoder;
import tw.intelegence.ncsist.sstp.utils.text.CommonString;
import tw.intelegence.ncsist.sstp.utils.text.NettyCode;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;


@RestController
@EnableAutoConfiguration
@RequestMapping("/user")
@Tag(name = "1. User api", description = "使用者")
public class UserController {

	@Autowired
	private UserService userService;


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
			user.setName("001");
			user.setLevel(1002L);
			user.setIp("192.168.50.46");
			user.setStudentId(2075001L);
			user.setStudentBatch(2023001L);
			user.setState(1L);
			user.setLongDate(longDate);
//		userService.save(user);
			userService.updateUser(user);

			//學生
			user.setUsername("2");
			user.setPassword(SHAEncoder.getSHA256("2"));
			user.setName("002");
			user.setLevel(1003L);
			user.setIp("192.168.50.46");
			user.setStudentId(2075002L);
			user.setStudentBatch(2023001L);
			user.setState(1L);
			user.setLongDate(longDate);
//		userService.save(user);
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

	public String doLogin(HttpServletRequest request, @RequestBody UserDTO userDTO){

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

			String sourceIp = request.getRemoteAddr();
			System.out.println("ip : " + sourceIp);
//			sourceIp = sourceIp.split(":")[0];
			sourceIp = "/" + sourceIp;
			System.out.println("new ip : " + sourceIp);
			

			ConnectController.sendClientsMsg(sourceIp, msgDTO);

			HttpSession session = request.getSession();
			session.setAttribute("user", user.getUsername());
			session.setAttribute( "level", user.getLevel());

			return CommonFunction.setResponse( CommonString.INFO_LOGIN_SUCCESS , user.getName());
		}else{
			return CommonFunction.setResponse( CommonString.INFO_LOGIN_FAIL, CommonString.MSG_LOGIN_FAIL_CHECK);
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
}
