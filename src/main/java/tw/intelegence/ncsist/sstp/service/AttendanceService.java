package tw.intelegence.ncsist.sstp.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.intelegence.ncsist.sstp.bean.Attendance;
import tw.intelegence.ncsist.sstp.repo.AttendanceRepository;

import java.util.List;

@Service("attendanceService")
public class AttendanceService {
	@Autowired
	private AttendanceRepository attendanceRepository;

//	public Long getAttendanceIdByUnitId(long unitId){
//		return attendanceRepository.findContentIdByUnitId(unitId);
//	}
//
//	public Long getAttendanceId(){
//		return attendanceRepository.findContentId();
//	}
//
//	public List<Attendance> getAttendanceList(){
//		return attendanceRepository.findContentsOrderByContentId();
//	}
//
//	public List<Attendance> getAttendanceListByUnitId(long unitId){
//		return attendanceRepository.findContentsByUnitIdOrderByContentId(unitId);
//	}
//
//	public Attendance getContentByContentId(long contentId){
//		return attendanceRepository.findContentByContentId(contentId);
//	}

	public List<Attendance> getAttendanceList(){
		return attendanceRepository.findAllAttendances();
	}

	public List<Attendance> getAttendanceListByUsername(String username){
		return attendanceRepository.findAttendancesByUsername(username);
	}

	@Transactional
	public Attendance saveAttendance(Attendance attendance){
		return attendanceRepository.save(attendance);
	}

	@Transactional
	public List<Attendance> deleteAttendance(Long id, String username){
		return attendanceRepository.delete(id, username);
	}

}
