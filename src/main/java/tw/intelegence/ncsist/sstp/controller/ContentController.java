package tw.intelegence.ncsist.sstp.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.intelegence.ncsist.sstp.bean.Content;
import tw.intelegence.ncsist.sstp.service.ContentService;

import java.time.LocalDate;
import java.util.List;


@RestController
@EnableAutoConfiguration
@RequestMapping("/content")
@Tag(name = "4. Content api", description = "測驗內容")
public class ContentController {

	@Autowired
	private ContentService contentService;

	@Operation(summary = "初始化",description = "無")
	@GetMapping("/init")
	public String init(){

		String message = "已有內容列表。";

		if(contentService.getContentList().isEmpty()){
			Long longDate = System.currentTimeMillis();
			long newId = createId(0L);

			Content content = new Content();
			content.setUnitId(2023001L);
			content.setContentId(newId);
//			content.setContent();
//			content.setContentOrder();
//			content.setQuizList("");
//			content.setGroup1();
//			content.setGroup2();
//			content.setGroup3();
//			content.setGroup4();
			content.setState(1L);
			content.setLongDate(longDate);

			message = "初始化內容列表完成";
		}


		return message;
	}

	@Operation(summary = "取得新內容ID", description = "無")
	@GetMapping("/getNewContentId/{unitId}")
	public ResponseEntity<Long> getNewContentId(@PathVariable Long unitId){
		Long newContentId = createId(contentService.getContentIdByUnitId(unitId));
		return ResponseEntity.ok(newContentId);
	}

	@Operation(summary = "取得所有內容", description = "無")
	@GetMapping("/getAllContentList/{unitId}")
	public ResponseEntity<List<Content>> getAllContentList(HttpServletRequest request, @PathVariable Long unitId){

		List<Content> contentList = getContentList(request, unitId);

		return ResponseEntity.ok(contentList);
	}


	@Operation(summary = "新增內容", description = "無")
	@PostMapping("/addContent")
	public ResponseEntity<List<Content>> addContent(HttpServletRequest request, @RequestBody Content content){

		contentService.saveContent(content);

		List<Content> contentList = getContentList(request, 0L);

		return ResponseEntity.ok(contentList);
	}

	@Operation(summary = "更新內容", description = "無")
	@PostMapping("/saveContent")
	public ResponseEntity<List<Content>> saveContent(HttpServletRequest request, @RequestBody Content content){

		contentService.saveContent(content);

		List<Content> contentList = getContentList(request, content.getUnitId());

		return ResponseEntity.ok(contentList);
	}


	@Operation(summary = "更新內容列表", description = "無")
//	@PutMapping("/updateContent/{contentId}")
	@PostMapping("/updateContent")
	public ResponseEntity<Content> updateContent(@RequestBody Content content){

		return ResponseEntity.ok(contentService.saveContent(content));
	}

	@Operation(summary = "刪除內容", description = "無")
	@PostMapping("/deleteContent")
	public ResponseEntity<List<Content>> deleteContent(@RequestBody Content content){

		return ResponseEntity.ok(contentService.deleteContent(content));
	}


	//取得
	private List<Content> getContentList(HttpServletRequest request, Long unitId){
		HttpSession session = request.getSession();
//		String user = String.valueOf(session.getAttribute("user"));
		int level = Integer.parseInt(String.valueOf(session.getAttribute("level")));

		List<Content> contentList;

		if(unitId > 0){
			contentList = contentService.getContentListByUnitId(unitId);
		}else{
			contentList = contentService.getContentList();
		}

		return contentList;
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
