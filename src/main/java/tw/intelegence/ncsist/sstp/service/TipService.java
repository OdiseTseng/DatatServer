package tw.intelegence.ncsist.sstp.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.intelegence.ncsist.sstp.bean.Tip;
import tw.intelegence.ncsist.sstp.repo.TipRepository;

import java.util.List;

@Service("tipService")
public class TipService {
	@Autowired
	private TipRepository tipRepository;

	public Long getTipId(){
		return tipRepository.findTipId();
	}

	public Long getTipIdByUnitId(long unitId){
		return tipRepository.findTipIdByUnitId(unitId);
	}

	public List<Tip> getTipList(){
		return tipRepository.findTipsOrderByContentId();
	}

	public List<Tip> getTipListByUnitId(long unitId){
		return tipRepository.findTipsByUnitIdOrderByContentId(unitId);
	}

	@Transactional
	public Tip saveTip(Tip tip){
		return tipRepository.save(tip);
	}


	@Transactional
	public List<Tip> deleteTip (long id, long unitId){
		return tipRepository.delete(id, unitId);
	}

}
