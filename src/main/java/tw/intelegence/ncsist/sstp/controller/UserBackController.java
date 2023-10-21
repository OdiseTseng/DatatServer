package tw.intelegence.ncsist.sstp.controller;

//import javax.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.intelegence.ncsist.sstp.bean.User;
import tw.intelegence.ncsist.sstp.model.UserDTO;
import tw.intelegence.ncsist.sstp.service.UserBackService;
import tw.intelegence.ncsist.sstp.utils.func.CommonFunction;
import tw.intelegence.ncsist.sstp.utils.func.SHAEncoder;
import tw.intelegence.ncsist.sstp.utils.text.CommonString;


@RestController
@EnableAutoConfiguration
@RequestMapping("/userback")
@Tag(name = "User back api", description = "interface")
public class UserBackController {

	@Autowired
	private UserBackService userService;

	@Operation(summary = "初始化",description = "無")
	@GetMapping("/init")
	public String init(){
//		User user = null;
//		for(int i=0;i<10;i++){
//			user = new User();
//			user.setUsername("test"+i);
//			user.setPassword( "pass" +i );
//			user.setName(i + "");
//			userService.save(user);
//		}
		User user = null;
		user = new User();
		user.setUsername("test1");
		user.setPassword(SHAEncoder.getSHA256("test1"));
		user.setName("001");
		userService.save(user);

		return "初始化使用者完成。";
	}

	@Operation(summary = "登入", description = "login")
//	@ApiResponse(responseCode = "200", description = "Login successful")
//	@ApiResponse(responseCode = "401", description = "Unauthorized")
	@PostMapping(value = "/login")
//	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//	public String doLogin(HttpServletRequest request, @RequestBody(description = "Map containing username and password", required = true,
//			content = @Content(schema = @Schema(implementation = Map.class))) Map<String, String> map){

	public String doLogin(HttpServletRequest request, @RequestBody UserDTO userDTO){

		System.out.println("userDTO: " + userDTO.toString());
			String username = userDTO.getUsername();
			String password = SHAEncoder.getSHA256(userDTO.getPassword());


			System.out.println("username: " + username);
			System.out.println("password: " + password);

//		LogDataHelper.writeLog("Route checkLogin & checkUsername username : " + username + " ;;; password :" + password);
//		LogDataHelper.writeLog("clientIP", request.getRemoteAddr());

		User user = userService.getByUsername( username );
		if(user != null){
			//return user.getPass().equals( password );
			if(user.getPassword().equals( password )){
//				String session = SessionManager.addSession(request.getRemoteAddr());
				String name = user.getName() != null || !user.getName().trim().equals( "" ) ? user.getName() : user.getUsername();
//				return CommonFunction.setResponse( CommonString.INFO_LOGIN_SUCCESS , name , session);
				HttpSession session = request.getSession();
				session.setAttribute("user", user.getUsername());
				session.setAttribute( "level", user.getLevel());

				return CommonFunction.setResponse( CommonString.INFO_LOGIN_SUCCESS , name);
			}else{
				return CommonFunction.setResponse( CommonString.INFO_LOGIN_FAIL, CommonString.MSG_LOGIN_FAIL_CHECK);
			}
		}
		return CommonFunction.setResponse( CommonString.INFO_LOGIN_FAIL ,CommonString.MSG_LOGIN_FAIL_CHECK);
	}

	@Operation(summary = "取得所有使用者", description = "")
	@GetMapping("/allUser")
	public ResponseEntity<Object> getAllUserBySession(@RequestParam("session") String session){
		return ResponseEntity.ok().body(userService.getAllUsers());
//		if(SessionManager.checkSession(session)){
//			return ResponseEntity.ok().body(userService.getAllUsers());
//		}else{
//			//放回應碼
//			return ResponseEntity.ok().body(CommonFunction.setResponse(CommonString.INFO_SESSION_TIMEOUT, CommonString.MSG_SESSION_TIMEOUT));
//		}
	}

	@GetMapping("/userByName/{username}")
	public User getUserByName(@PathVariable("username") String username){
		return userService.getByUsername(username);
	}

	@GetMapping("/userById/{userid}")
	public User getUserById(@PathVariable("userid") Long userid){
		return userService.getUserByID(userid);
	}

	@GetMapping("/page")
	public Page<User> getPage(){
		return userService.findPage();
	}

	@GetMapping("/page/{maxID}")
	public Page<User> getPageByMaxID(@PathVariable("maxID") Long maxID){
		return userService.find(maxID);
	}

	@RequestMapping("/update/{id}/{name}")
	public User update(@PathVariable Long id, @PathVariable String name){
		return userService.update(id,name);
	}

	@RequestMapping("/update/{id}")
	public Boolean updateById(@PathVariable Long id){
		return userService.updateById("newName",id);
	}


}
