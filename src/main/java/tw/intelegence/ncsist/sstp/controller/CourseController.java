package tw.intelegence.ncsist.sstp.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.intelegence.ncsist.sstp.bean.Course;
import tw.intelegence.ncsist.sstp.model.CourseDTO;
import tw.intelegence.ncsist.sstp.service.CourseService;
import tw.intelegence.ncsist.sstp.session.SessionContext;

import java.time.LocalDate;
import java.util.Enumeration;
import java.util.List;


@RestController
@EnableAutoConfiguration
@RequestMapping("/course")
@Tag(name = "2. Course api", description = "課程")
public class CourseController {

	@Autowired
	private CourseService courseService;

	@Operation(summary = "初始化",description = "無")
	@GetMapping("/init")
	public String init(){

		String message = "已有課程列表。";

		if(courseService.getSortCourseList().isEmpty()){
			long longDate = System.currentTimeMillis();

			long newId = createCourseId(0L);

			System.out.println("1st currenCcourseId : " + newId);

			Course course = new Course();
			course.setCourseId(newId);
			course.setCourseType(1L);
			course.setCourseName("安裝教學");
			course.setCourseSchedule(20L);
			course.setCourseDesc("安裝軟體所需的其他輔助套件");
			course.setCreditUnits(2L);
//			course.setUnitList("");
			course.setState(1L);
			course.setLongDate(longDate);

			courseService.saveCourse(course);

			newId = createCourseId(newId);
			System.out.println("2nd currenCcourseId : " + newId);

			course.setCourseId(newId);
			course.setCourseType(1L);
			course.setCourseName("操作實作");
			course.setCourseSchedule(30L);
			course.setCourseDesc("實際演練操作流程");
			course.setCreditUnits(6L);
//			course.setUnitList("");
			course.setState(1L);
			course.setLongDate(longDate);

			courseService.saveCourse(course);

			newId = createCourseId(newId);
			System.out.println("3rd currenCcourseId : " + newId);

			course.setCourseId(newId);
			course.setCourseType(2L);
			course.setCourseName("信號源資料庫建置");
			course.setCourseSchedule(30L);
			course.setCourseDesc("介紹軟體的發展方向及內容");
			course.setCreditUnits(6L);
//			course.setUnitList("");
			course.setState(1L);
			course.setLongDate(longDate);

			courseService.saveCourse(course);

			newId = createCourseId(newId);
			System.out.println("4th currenCcourseId : " + newId);

			course.setCourseId(newId);
			course.setCourseType(2L);
			course.setCourseName("軟體教學");
			course.setCourseSchedule(60L);
			course.setCourseDesc("透過此次教學，更清楚如何實作此系統");
			course.setCreditUnits(12L);
//			course.setUnitList("");
			course.setState(1L);
			course.setLongDate(longDate);

			courseService.saveCourse(course);

			newId = createCourseId(newId);
			System.out.println("5th currenCcourseId : " + newId);

			course.setCourseId(newId);
			course.setCourseType(1L);
			course.setCourseName("整合應用");
			course.setCourseSchedule(60L);
			course.setCourseDesc("整合應用描述");
			course.setCreditUnits(12L);
//			course.setUnitList("");
			course.setState(1L);
			course.setLongDate(longDate);

			courseService.saveCourse(course);

			newId = createCourseId(newId);
			System.out.println("6th currenCcourseId : " + newId);

			course.setCourseId(newId);
			course.setCourseType(1L);
			course.setCourseName("信號偵蒐測向");
			course.setCourseSchedule(45L);
			course.setCourseDesc("信號偵蒐測向描述");
			course.setCreditUnits(9L);
//			course.setUnitList("");
			course.setState(1L);
			course.setLongDate(longDate);

			courseService.saveCourse(course);

			newId = createCourseId(newId);
			System.out.println("7th currenCcourseId : " + newId);

			course.setCourseId(newId);
			course.setCourseType(1L);
			course.setCourseName("信號源資料庫建置");
			course.setCourseSchedule(45L);
			course.setCourseDesc("信號源資料庫建置");
			course.setCreditUnits(3L);
//			course.setUnitList("");
			course.setState(1L);
			course.setLongDate(longDate);

			courseService.saveCourse(course);

			message = "初始化課程完成。";
		}

		return message;
	}


	@Operation(summary = "取得新課程ID", description = "無")
	@GetMapping("/getNewCourseId")
	public ResponseEntity<Long> getNewCourseId(){
		Long newCourseId = createCourseId(courseService.getCourseId());

		return ResponseEntity.ok(newCourseId);
	}

	@Operation(summary = "取得所有課程", description = "無")
	@GetMapping("/getAllCourse")
	public ResponseEntity<List<Course>> getAllCourse(HttpServletRequest request){

		List<Course> courseList = getCourseList(request);

		return ResponseEntity.ok(courseList);
	}


	@Operation(summary = "新增課程", description = "無")
	@PostMapping("/addCourse")
	public ResponseEntity<List<Course>> addCourse(HttpServletRequest request, @RequestBody CourseDTO courseDTO){

		Course newCourse = new Course();
		newCourse.setCourseName(courseDTO.getCourseName());
		newCourse.setCourseType(courseDTO.getCourseType());
		newCourse.setCourseSchedule(courseDTO.getCourseSchedule());
		newCourse.setCreditUnits(courseDTO.getCreditUnits());
		newCourse.setUnitList(courseDTO.getUnitList());

		courseService.saveCourse(newCourse);

		List<Course> courseList = getCourseList(request);

		return ResponseEntity.ok(courseList);
	}

	@Operation(summary = "更新課程", description = "無")
	@PostMapping("/saveCourse")
//	@PutMapping("/saveCourse/{courseId}")
	public ResponseEntity<List<Course>> saveCourse(HttpServletRequest request, @RequestBody Course course){

		courseService.saveCourse(course);

		List<Course> courseList = getCourseList(request);

		return ResponseEntity.ok(courseList);
	}


	@Operation(summary = "更新課程單元列表", description = "無")
//	@PostMapping("/updateCourseUnits")
	@PutMapping("/updateCourseUnitList/{courseId}/{unitList}")
	public ResponseEntity<Course> updateCourseUnitList(@PathVariable Long courseId, @PathVariable String unitList){

		Course existCourse = courseService.getCourseByCourseId(courseId);
		existCourse.setUnitList(unitList);
		return ResponseEntity.ok(courseService.saveCourse(existCourse));
	}

	@Operation(summary = "刪除課程", description = "無")
	@PostMapping("/deleteCourse")
	public ResponseEntity<List<Course>> deleteCourse(@RequestBody Course course){
		return ResponseEntity.ok(courseService.deleteCourse(course));
	}

	//取得
	private List<Course> getCourseList(HttpServletRequest request){
//		HttpSession session = request.getSession();
//		Enumeration<String> attributeNames = session.getAttributeNames();
//		while (attributeNames.hasMoreElements()) {
//			String attributeName = attributeNames.nextElement();
//			Object attributeValue = session.getAttribute(attributeName);
//			System.out.println("attributeName : " + attributeName + " ; attributeValue : " + attributeValue);
//		}
		String sessionId = request.getRequestedSessionId();
		System.out.println("sessionId : " + sessionId);

		sessionId = sessionId.split("=")[1];

		System.out.println("sessionId : " + sessionId);

		SessionContext sessionContext = SessionContext.getInstance();
		HttpSession session = sessionContext.getSession(sessionId);



//		ServletContext context = request.getServletContext();
//		HttpSession session = (HttpSession)context.getAttribute(sessionId);
//
		Enumeration<String> attributeNames = session.getAttributeNames();

		while (attributeNames.hasMoreElements()) {
			String attributeName = attributeNames.nextElement();
			Object attributeValue = session.getAttribute(attributeName);
			System.out.println("attributeName : " + attributeName + " ; attributeValue : " + attributeValue);
		}

//		String user = String.valueOf(session.getAttribute("user"));
		int level = Integer.parseInt(String.valueOf(session.getAttribute("level")));

		long courseType = 1;
		switch(level){
			case 1001:
				courseType = 0;
				break;
			case 1002:
				courseType = 2;
				break;
			case 1003:
			case 1004:
//				courseType = 1;
				break;
		}

		List<Course> courseList;
		if(courseType > 0){
			courseList = courseService.getCourseListByCourseType(courseType);
		}else{
			courseList = courseService.getSortCourseList();
		}

		return courseList;
	}

	private long createCourseId(long courseId){
		String courseIdString = courseId + "";
		LocalDate localDate = LocalDate.now();
		long newCourseId = 0L;
		long newYear = localDate.getYear() * 1000 * 100;
		long newMonth = localDate.getMonth().getValue() * 1000;

		if(courseId > 1 && courseId > newYear && Long.parseLong(courseIdString.substring(4)) > newMonth){
			newCourseId = ++courseId;
		}

		if(newCourseId < 1){
			newCourseId += newYear;
			newCourseId += newMonth;
			newCourseId++;
		}

		return newCourseId;
	}

}
