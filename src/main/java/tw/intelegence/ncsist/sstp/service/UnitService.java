package tw.intelegence.ncsist.sstp.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.intelegence.ncsist.sstp.bean.Unit;
import tw.intelegence.ncsist.sstp.repo.UnitRepository;

import java.util.List;

@Service("unitService")
public class UnitService {
	@Autowired
	private UnitRepository unitRepository;

	public Long getUnitId(){
		return unitRepository.findByUnitId();
	}

	public Unit getUnitByUnitId(Long unitId){
		return unitRepository.findUnitByUnitId(unitId);
	}

	public List<Unit> getAllUnitList(){
		return unitRepository.findAllOrderByUnitId();
	}

	public List<Unit> getUnitListByCourseId(Long courseId){
		return unitRepository.findByCourseIdOrderByUnitId(courseId);
	}

	@Transactional
	public Unit saveUnit(Unit unit){
		return unitRepository.save(unit);
	}

	@Transactional
	public List<Unit> deleteUnit(Unit unit) {
		return unitRepository.delete(unit);
	}

}
