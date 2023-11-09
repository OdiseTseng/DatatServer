package tw.intelegence.ncsist.sstp.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.intelegence.ncsist.sstp.bean.Attendance;
import tw.intelegence.ncsist.sstp.service.AttendanceService;
import tw.intelegence.ncsist.sstp.session.SessionContext;

import java.time.LocalDate;
import java.sql.Date;
import java.util.Enumeration;
import java.util.List;


@RestController
@EnableAutoConfiguration
@RequestMapping("/attendance")

@Tag(name = "9. Attendance api", description = "測驗紀錄")
public class AttendanceController {

	@Autowired
	private AttendanceService attendanceService;

	@Operation(summary = "初始化",description = "無")
	@GetMapping("/init")
	public String init(){

		String message = "已有紀錄列表。";

		if(attendanceService.getAttendanceListByUsername("1").isEmpty() ||
				attendanceService.getAttendanceListByUsername("2").isEmpty()) {
			long longDate = System.currentTimeMillis();
			Date date = new Date(longDate);

			long newId = createAttendanceId(0L);

			Attendance attendance = new Attendance();

			attendance.setAttendanceId(newId);
			attendance.setCourseId(202311001L);
			attendance.setUnitId(2023001L);
			attendance.setContentId(0L);
			attendance.setQuizId(0L);
			attendance.setUsername("2");
			attendance.setAttendanceDate(date);
			attendance.setTeam(1L);
			attendance.setRole(3L);
			attendance.setRecordScore(60L);
			attendance.setRecordShot(""); //圖片檔案
			attendance.setScore(60L);
			attendance.setState(1L);
			attendance.setLongDate(longDate);

			System.out.println("attendance : " + attendance);

			attendanceService.saveAttendance(attendance);

			newId = createAttendanceId(newId);

			attendance.setAttendanceId(newId);
			attendance.setCourseId(202310001L);
			attendance.setUnitId(2023001L);
			attendance.setContentId(0L);
			attendance.setQuizId(0L);
			attendance.setUsername("3");
			attendance.setAttendanceDate(date);
			attendance.setTeam(1L);
			attendance.setRole(2L);
			attendance.setRecordScore(60L);
			attendance.setRecordShot(""); //圖片檔案
			attendance.setScore(60L);
			attendance.setState(1L);
			attendance.setLongDate(longDate);

			System.out.println("attendance : " + attendance);

			attendanceService.saveAttendance(attendance);

			newId = createAttendanceId(newId);

			attendance.setAttendanceId(newId);
			attendance.setCourseId(202311001L);
			attendance.setUnitId(2023001L);
			attendance.setContentId(0L);
			attendance.setQuizId(0L);
			attendance.setUsername("4");
			attendance.setAttendanceDate(date);
			attendance.setTeam(1L);
			attendance.setRole(1L);
			attendance.setRecordScore(60L);
			attendance.setRecordShot(""); //圖片檔案
			attendance.setScore(60L);
			attendance.setState(1L);
			attendance.setLongDate(longDate);

			System.out.println("attendance : " + attendance);

			attendanceService.saveAttendance(attendance);

			newId = createAttendanceId(newId);

			attendance.setAttendanceId(newId);
			attendance.setCourseId(6L);
			attendance.setUnitId(2023001L);
			attendance.setContentId(0L);
			attendance.setQuizId(0L);
			attendance.setUsername("4");
			attendance.setAttendanceDate(date);
			attendance.setTeam(1L);
			attendance.setRole(1L);
			attendance.setRecordScore(60L);
			attendance.setRecordShot(""); //圖片檔案
			attendance.setScore(60L);
			attendance.setState(1L);
			attendance.setLongDate(longDate);

			System.out.println("attendance : " + attendance);

			attendanceService.saveAttendance(attendance);

			message = "初始化紀錄列表完成。";
		}


		return message;
	}

	@Operation(summary = "取得所有紀錄", description = "無")
	@GetMapping("/getAllAttendanceList")
	public ResponseEntity<List<Attendance>> getAllAttendanceList(HttpServletRequest request){

		return ResponseEntity.ok(getAttendanceList(request));
	}


	@Operation(summary = "新增紀錄", description = "無")
	@PostMapping("/addAttendance")
	public ResponseEntity<List<Attendance>> addAttendance(HttpServletRequest request, @RequestBody Attendance attendance){


		List<Attendance> attendanceList = getAttendanceList(request);
			Attendance lastAttendance = attendanceList.get(attendanceList.size() - 1);
			Long newId = createAttendanceId(lastAttendance.getAttendanceId());
			attendance.setAttendanceId(newId);
		attendanceService.saveAttendance(attendance);


		return ResponseEntity.ok(attendanceList);
	}

	@Operation(summary = "更新紀錄", description = "無")
	@PostMapping("/saveAttendance")
	public ResponseEntity<List<Attendance>> saveContent(HttpServletRequest request, @RequestBody Attendance attendance){
		attendanceService.saveAttendance(attendance);

		return ResponseEntity.ok(getAttendanceList(request));
	}

	@Operation(summary = "刪除紀錄", description = "無")
	@PostMapping("/deleteAttendance")
	public ResponseEntity<List<Attendance>> deleteAttendance(@RequestBody Attendance attendance){

		Long attendanceId = attendance.getId();
		String username = attendance.getUsername();;

		return ResponseEntity.ok(attendanceService.deleteAttendance(attendanceId, username));
	}

	//取得
	private List<Attendance> getAttendanceList(HttpServletRequest request){
//		HttpSession session = request.getSession();
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

		String user = String.valueOf(session.getAttribute("user"));

		int level = Integer.parseInt(String.valueOf(session.getAttribute("level")));

		if(level != 1002){
			//新增一個取得所有人紀錄的功能ByMS使用
			return attendanceService.getAttendanceListByUsername(user);
		}else{
			return attendanceService.getAttendanceList();
		}
	}

	private long createAttendanceId(long id){
		System.out.println("createAttendanceId : " + id);
		String idString = id + "";
		LocalDate localDate = LocalDate.now();
		long newId = 0L;
		long newYear = localDate.getYear() * 1000 * 100;
		long newMonth = localDate.getMonth().getValue() * 1000;

		if(id > 1 && id > newYear && Long.parseLong(idString.substring(4)) > newMonth){
			newId = ++id;
		}

		if(newId < 1){
			newId += newYear;
			newId += newMonth;
			newId++;
		}

		System.out.println("newId : " + newId);
		return newId;
	}

}
