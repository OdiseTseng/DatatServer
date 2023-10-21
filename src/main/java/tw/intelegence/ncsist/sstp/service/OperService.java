package tw.intelegence.ncsist.sstp.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.intelegence.ncsist.sstp.bean.Oper;
import tw.intelegence.ncsist.sstp.repo.OperRepository;

import java.util.List;

@Service("operService")
public class OperService {
	@Autowired
	private OperRepository operRepository;

	public Long getOperId(){
		return operRepository.findOperId();
	}

	public List<Oper> getOperList(){
		return operRepository.findAllOpersOrderByContentId();
	}

	public List<Oper> getOperListByUnitId(long unitId){
		return operRepository.findOpersByUnitIdOrderByContentId(unitId);
	}

	@Transactional
	public Oper saveOper(Oper oper){
		return operRepository.save(oper);
	}


	@Transactional
	public List<Oper> deleteOper (long id, long unitId){
		return operRepository.delete(id, unitId);
	}

}
