package tw.intelegence.ncsist.sstp.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//import tw.intelegence.ncsist.sstp.bean.Course;
import tw.intelegence.ncsist.sstp.bean.Unit;

import java.util.List;

@Repository
public interface UnitBackRepository extends JpaRepository<Unit, Long> {

//    @Query("SELECT c.courseName, c.creditUnits FROM Course c WHERE c.courseName LIKE %:courseName%")
//    List<Object[]> searchCourses(@Param("courseName") String keyword);
//    List<Course> findByCourseType(int courseType);
//    Page<Unit> find(Pageable pageable, int courseType);
    Page<Unit> findUnitsByIdIn(Pageable pageable, List<Long> unitIds);

//    Page<Unit> findAll(Pageable pageable);

//    Unit findByCourseId(long courseId);

    Unit save(Unit unit);
//    Course saveByCouseIdAndUnitList(Course course, String unitList);

    // 查询操作
//    List<Unit> findByCourseName(String courseName);
//    void deleteCourseByCourseId(Long courseId);
}
