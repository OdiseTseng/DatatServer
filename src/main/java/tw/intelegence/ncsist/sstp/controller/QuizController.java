package tw.intelegence.ncsist.sstp.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.intelegence.ncsist.sstp.bean.Quiz;
import tw.intelegence.ncsist.sstp.service.QuizService;
import tw.intelegence.ncsist.sstp.session.SessionContext;

import java.time.LocalDate;
import java.util.Enumeration;
import java.util.List;


@RestController
@EnableAutoConfiguration
@RequestMapping("/quiz")
@Tag(name = "7. Quiz api", description = "測驗題")
public class QuizController {

	@Autowired
	private QuizService quizService;

	@Operation(summary = "初始化",description = "無")
	@GetMapping("/init")
	public String init(){

		String message = "已有測驗列表。";

		if(quizService.getQuizList().isEmpty()){
			long longDate = System.currentTimeMillis();
			long newId = createId(0L);

			long quizId = 0L;
			Quiz quiz = new Quiz();
			quiz.setUnitId(2023001L);
			quiz.setQuizId(newId);
			quiz.setTitle("測驗操作");
			quiz.setContent("請根據應用程式上的指示，完成操作並回答以下是非題");
			quiz.setTofQuiz(0L);
			quiz.setEssayQuiz(1L);
			quiz.setAnswer("遵守按鈕");
			quiz.setState(1L);
			quiz.setLongDate(longDate);

			quizService.saveQuiz(quiz);

			newId = createId(newId);

			quiz.setQuizId(2023001L);
			quiz.setUnitId(newId);
			quiz.setTitle("測驗操作");
			quiz.setContent("請問剛剛進來前須先選擇課程是否正確?");
			quiz.setTofQuiz(0L);
			quiz.setEssayQuiz(1L);
			quiz.setAnswer("1");
			quiz.setState(1L);
			quiz.setLongDate(longDate);

			quizService.saveQuiz(quiz);

			newId = createId(newId);

			quiz.setQuizId(2023001L);
			quiz.setUnitId(newId);
			quiz.setTitle("信號搜索測驗");
			quiz.setContent("偵搜車到達現場時，通訊兵打開搜索系統，經過搜索後，請問最可疑的無人機訊號可能是??");
			quiz.setTofQuiz(0L);
			quiz.setEssayQuiz(1L);
			quiz.setAnswer("1");
			quiz.setState(1L);
			quiz.setLongDate(longDate);

			quizService.saveQuiz(quiz);

			quiz.setQuizId(2023002L);
			quiz.setUnitId(newId);
			quiz.setTitle("信號搜索是非題");
			quiz.setContent("請問操作上是否可以不聽從長官指示自行調整截收準位?");
			quiz.setTofQuiz(0L);
			quiz.setEssayQuiz(1L);
			quiz.setAnswer("1");
			quiz.setState(1L);
			quiz.setLongDate(longDate);

			quizService.saveQuiz(quiz);

			quiz.setUnitId(5L);
			quiz.setTitle("信號搜索測驗");
			quiz.setContent("偵搜車到達現場時，通訊兵打開搜索系統，經過搜索後，請問最可疑的無人機訊號可能是?");
			quiz.setTofQuiz(2L);
			quiz.setEssayQuiz(1L);
			quiz.setAnswer("477");
			quiz.setState(1L);
			quiz.setLongDate(longDate);

			quizService.saveQuiz(quiz);

			quiz.setUnitId(5L);
			quiz.setTitle("信號搜索是非題");
			quiz.setContent("請問操作上是否可以不聽從長官指示自行調整截收準位?");
			quiz.setTofQuiz(1L);
			quiz.setEssayQuiz(2L);
			quiz.setAnswer("否");
			quiz.setState(1L);
			quiz.setLongDate(longDate);

			quizService.saveQuiz(quiz);

			quiz.setUnitId(39L);
			quiz.setTitle("信號搜索測驗");
			quiz.setContent("(團體)偵搜車到達現場時，通訊兵打開搜索系統，經過搜索後，請問最可疑的無人機訊號可能是?");
			quiz.setTofQuiz(2L);
			quiz.setEssayQuiz(1L);
			quiz.setAnswer("477");
			quiz.setState(1L);
			quiz.setLongDate(longDate);

			quizService.saveQuiz(quiz);

			quiz.setUnitId(39L);
			quiz.setTitle("信號搜索是非題");
			quiz.setContent("(團體)請問操作上是否可以不聽從長官指示自行調整截收準位?");
			quiz.setTofQuiz(1L);
			quiz.setEssayQuiz(2L);
			quiz.setAnswer("否");
			quiz.setState(1L);
			quiz.setLongDate(longDate);

			quizService.saveQuiz(quiz);

			message = "初始化測驗列表完成";
		}


		return message;
	}


	@Operation(summary = "取得新測驗ID", description = "無")
	@GetMapping("/getNewQuizId")
	public ResponseEntity<Long> getNewQuizId(){
		Long newQuizId = createId(quizService.getQuizId());

		return ResponseEntity.ok(newQuizId);
	}

	@Operation(summary = "取得所有測驗", description = "無")
	@GetMapping("/getAllQuizList")
	public ResponseEntity<List<Quiz>> getAllQuizList(HttpServletRequest request){

		List<Quiz> quizList = getQuizList(request, 0L);

		return ResponseEntity.ok(quizList);
	}

	@Operation(summary = "取得所有測驗", description = "無")
	@GetMapping("/getQuizListByUnitId/{unitId}")
	public ResponseEntity<List<Quiz>> getQuizListByUnitId(HttpServletRequest request, @PathVariable Long unitId){

		List<Quiz> quizList = getQuizList(request, unitId);

		return ResponseEntity.ok(quizList);
	}

	@Operation(summary = "新增測驗", description = "無")
	@PostMapping("/addQuiz")
	public ResponseEntity<List<Quiz>> addQuiz(HttpServletRequest request, @RequestBody Quiz quiz){

		quizService.saveQuiz(quiz);

		List<Quiz> quizList = getQuizList(request, quiz.getUnitId());

		return ResponseEntity.ok(quizList);
	}

	@Operation(summary = "更新測驗", description = "無")
	@PostMapping("/saveQuiz")
	public ResponseEntity<List<Quiz>> saveQuiz(HttpServletRequest request, @RequestBody Quiz quiz){

		quizService.saveQuiz(quiz);

		List<Quiz> quizList = getQuizList(request, quiz.getUnitId());

		return ResponseEntity.ok(quizList);
	}

	@Operation(summary = "刪除測驗", description = "無")
	@PostMapping("/deleteQuiz")
	public ResponseEntity<List<Quiz>> deleteQuiz(@RequestBody Quiz quiz){

		return ResponseEntity.ok(quizService.deleteQuiz(quiz));
	}

	//取得
	private List<Quiz> getQuizList(HttpServletRequest request, Long unitId){
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

		List<Quiz> quizList;
		if(unitId > 0){
			quizList = quizService.getQuizListByUnitId(unitId);
		}else{
			quizList = quizService.getQuizList();
		}

		return quizList;
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
