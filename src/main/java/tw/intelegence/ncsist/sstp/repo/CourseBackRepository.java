package tw.intelegence.ncsist.sstp.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.intelegence.ncsist.sstp.bean.Course;

import java.util.List;

@Repository
public interface CourseBackRepository extends JpaRepository<Course, Long> {

//    @Query("SELECT c.courseName, c.creditUnits FROM Course c WHERE c.courseName LIKE %:courseName%")
//    List<Object[]> searchCourses(@Param("courseName") String keyword);
////    List<Course> findByCourseType(int courseType);
//    Page<Course> findByCourseType(Pageable pageable, long courseType);
    Page<Course> findByCourseType(Pageable pageable, long courseType);
//    Page<Course> findAllOrderByCourseTypeAndCourseId(Pageable pageable);
    Page<Course> findAll(Pageable pageable);
    Course findByCourseId(long courseId);

    Course save(Course course);

    // 查询操作
    List<Course> findByCourseName(String courseName);
    void deleteCourseByCourseId(Long courseId);
}
