package tw.intelegence.ncsist.sstp.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.intelegence.ncsist.sstp.bean.Course;
import tw.intelegence.ncsist.sstp.repo.CourseRepository;

import java.util.List;

@Service("courseService")
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;

	public Long getCourseId(){
		return courseRepository.findCourseId();
	}

	public List<Course> getSortCourseList(){
		return courseRepository.findAllOrderByCourseTypeAndCourseId();
	}

	public List<Course> getCourseListByCourseType(long courseType){
		return courseRepository.findAllOrderByCourseTypeAndCourseId(courseType);
	}

	public Course getCourseByCourseId(long courseId){
		return courseRepository.findByCourseId(courseId);
	}


	@Transactional
	public Course saveCourse(Course course){

		return courseRepository.save(course);
	}

	@Transactional
	public List<Course> deleteCourse(Course course){
		return courseRepository.delete(course);
	}

}
