package tw.intelegence.ncsist.sstp.repo;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tw.intelegence.ncsist.sstp.bean.Course;
import tw.intelegence.ncsist.sstp.bean.QCourse;

import java.util.List;

@Repository
public class CourseRepository {
//    @Autowired
//    private EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public CourseRepository(EntityManager entityManager){
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public Long findCourseId(){

        Long courseId = 0L;
//        if(queryFactory == null){
//            queryFactory = new JPAQueryFactory(entityManager);
//        }

        QCourse qCourse = QCourse.course;
        Course course = queryFactory.selectFrom(qCourse).orderBy(qCourse.courseId.desc()).fetchFirst();

        if(course != null){
            courseId = course.getCourseId();
        }

        return courseId;
    }


    public Course findByCourseId(long courseId){

//        if(queryFactory == null){
//            queryFactory = new JPAQueryFactory(entityManager);
//        }

        QCourse qCourse = QCourse.course;

        return queryFactory.selectFrom(qCourse).where(qCourse.courseId.eq(courseId)).fetchFirst();
    }

    public List<Course> findAllOrderByCourseTypeAndCourseId(){

//        if(queryFactory == null){
//            queryFactory = new JPAQueryFactory(entityManager);
//        }

        QCourse qCourse = QCourse.course;

//        OrderSpecifier<Long> orderASC = new OrderSpecifier<>(Order.ASC, qCourse.courseType);

//        return queryFactory.selectFrom(qCourse).orderBy(orderASC).fetch();
        return queryFactory.selectFrom(qCourse).orderBy(qCourse.courseType.asc()).fetch();
    }

    public List<Course> findAllOrderByCourseTypeAndCourseId(long courseType){

//        if(queryFactory == null){
//            queryFactory = new JPAQueryFactory(entityManager);
//        }

        QCourse qCourse = QCourse.course;

        OrderSpecifier<Long> orderASC = new OrderSpecifier<>(Order.ASC, qCourse.courseType);

        return queryFactory.selectFrom(qCourse).where(qCourse.courseType.eq(courseType)).orderBy(orderASC).fetch();
    }

    public Course save(Course course){

//        if(queryFactory == null){
//            queryFactory = new JPAQueryFactory(entityManager);
//        }

        QCourse qCourse = QCourse.course;

//        Course fCourse = null;
//
//        if(course.getId() == null){
//            queryFactory.selectFrom(qCourse).where(qCourse.id.eq(course.getId()).and(qCourse.courseId.eq(course.getCourseId()))).fetchFirst();
//        }


        if(course.getId() == null){

            queryFactory.insert(qCourse)
                    .columns(qCourse.courseId, qCourse.courseType, qCourse.courseName, qCourse.courseType, qCourse.courseSchedule, qCourse.courseDesc, qCourse.creditUnits, qCourse.longDate, qCourse.state, qCourse.unitList)
                    .values(course.getCourseId(), course.getCourseType(), course.getCourseName(), course.getCourseType(), course.getCourseSchedule(), course.getCourseDesc(), course.getCreditUnits(), course.getLongDate(), course.getState(), course.getUnitList())
                    .execute();

        }else{

            queryFactory.update(qCourse)
                    .set(qCourse.courseType, course.getCourseType())
                    .set(qCourse.courseName, course.getCourseName())
                    .set(qCourse.courseDesc, course.getCourseDesc())
                    .set(qCourse.courseSchedule, course.getCourseSchedule())
                    .set(qCourse.creditUnits, course.getCreditUnits())
                    .set(qCourse.longDate, course.getLongDate())
                    .set(qCourse.state, course.getState())
                    .set(qCourse.unitList, course.getUnitList())
                    .where(qCourse.id.eq(course.getId()).and(qCourse.courseId.eq(course.getCourseId())))
                    .execute();
        }

        return queryFactory.selectFrom(qCourse).where(qCourse.courseId.eq(course.getCourseId())).fetchFirst();
    }

    public List<Course> delete(Course course){
        QCourse qCourse = QCourse.course;

        queryFactory.delete(qCourse)
                .where(qCourse.id.eq(course.getId()))
                .execute();

        List<Course> courseList;

        if(course.getCourseType() == 0){
            courseList = findAllOrderByCourseTypeAndCourseId();
        }else{
            courseList = findAllOrderByCourseTypeAndCourseId(course.getCourseType());
        }

        return courseList;
    }


}
