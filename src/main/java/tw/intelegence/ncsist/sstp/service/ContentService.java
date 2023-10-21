package tw.intelegence.ncsist.sstp.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.intelegence.ncsist.sstp.bean.Content;
import tw.intelegence.ncsist.sstp.repo.ContentRepository;

import java.util.List;

@Service("contentService")
public class ContentService {
	@Autowired
	private ContentRepository contentRepository;


	public Long getContentIdByUnitId(long unitId){
		return contentRepository.findContentIdByUnitId(unitId);
	}

	public Long getContentId(){
		return contentRepository.findContentId();
	}

	public List<Content> getContentList(){
		return contentRepository.findContentsOrderByContentId();
	}

	public List<Content> getContentListByUnitId(long unitId){
//		return contentRepository.findContentsByUnitIdOrderByContentId(unitId);
		return contentRepository.findContentsByUnitIdOrderByContentOrder(unitId);
	}

	public Content getContentByContentId(long contentId){
		return contentRepository.findContentByContentId(contentId);
	}


	@Transactional
	public Content saveContent(Content content){
		return contentRepository.save(content);
	}

	@Transactional
	public List<Content> deleteContent(Content content) {
		return contentRepository.delete(content);
	}

}
