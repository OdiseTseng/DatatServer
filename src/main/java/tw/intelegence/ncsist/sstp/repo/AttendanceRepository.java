package tw.intelegence.ncsist.sstp.repo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tw.intelegence.ncsist.sstp.bean.Attendance;
import tw.intelegence.ncsist.sstp.bean.QAttendance;

import java.util.List;

@Repository
public class AttendanceRepository {
    @Autowired
    private EntityManager entityManager;
    private JPAQueryFactory queryFactory;

//    public Long findContentId(){
//
//        if(queryFactory == null){
//            queryFactory = new JPAQueryFactory(entityManager);
//        }
//
//        QAttendance qAttendance = QAttendance.attendance;
//        Attendance attendance = queryFactory.selectFrom(qAttendance).orderBy(qAttendance.contentId.desc()).fetchFirst();
//
//        return attendance.getContentId();
//    }

    public Long findAttendanceIdByCourseId(long courseId){

        if(queryFactory == null){
            queryFactory = new JPAQueryFactory(entityManager);
        }

        QAttendance qAttendance = QAttendance.attendance;

        Attendance attendance = queryFactory.selectFrom(qAttendance).where(qAttendance.courseId.eq(courseId)).orderBy(qAttendance.contentId.desc()).fetchFirst();

        long attendanceId = 0L;
        if(attendance != null){
            attendanceId = attendance.getId();
        }
        return attendanceId;
    }

    public Long findAttendanceIdByUnitId(long unitId){

        if(queryFactory == null){
            queryFactory = new JPAQueryFactory(entityManager);
        }

        QAttendance qAttendance = QAttendance.attendance;

        Attendance attendance = queryFactory.selectFrom(qAttendance).where(qAttendance.unitId.eq(unitId)).orderBy(qAttendance.contentId.desc()).fetchFirst();

        long attendanceId = 0L;
        if(attendance != null){
            attendanceId = attendance.getId();
        }
        return attendanceId;
    }

    public Long findAttendanceIdByContentId(long contentId){

        if(queryFactory == null){
            queryFactory = new JPAQueryFactory(entityManager);
        }

        QAttendance qAttendance = QAttendance.attendance;

        Attendance attendance = queryFactory.selectFrom(qAttendance).where(qAttendance.contentId.eq(contentId)).orderBy(qAttendance.contentId.desc()).fetchFirst();

        long attendanceId = 0L;
        if(attendance != null){
            attendanceId = attendance.getId();
        }

        return attendanceId;
    }

    public Long findAttendanceIdByQuizId(long quizId){

        if(queryFactory == null){
            queryFactory = new JPAQueryFactory(entityManager);
        }

        QAttendance qAttendance = QAttendance.attendance;

        Attendance attendance = queryFactory.selectFrom(qAttendance).where(qAttendance.quizId.eq(quizId)).orderBy(qAttendance.contentId.desc()).fetchFirst();

        long attendanceId = 0L;
        if(attendance != null){
            attendanceId = attendance.getId();
        }

        return attendanceId;
    }

    public Attendance findAttendanceByCourseId(long courseId){

        if(queryFactory == null){
            queryFactory = new JPAQueryFactory(entityManager);
        }

        QAttendance qAttendance = QAttendance.attendance;

        return queryFactory.selectFrom(qAttendance).where(qAttendance.courseId.eq(courseId)).fetchFirst();
    }

    public Attendance findAttendanceByUnitId(long unitId){

        if(queryFactory == null){
            queryFactory = new JPAQueryFactory(entityManager);
        }

        QAttendance qAttendance = QAttendance.attendance;

        return queryFactory.selectFrom(qAttendance).where(qAttendance.unitId.eq(unitId)).fetchFirst();
    }

    public Attendance findAttendanceByContentId(long contentId){

        if(queryFactory == null){
            queryFactory = new JPAQueryFactory(entityManager);
        }

        QAttendance qAttendance = QAttendance.attendance;

        return queryFactory.selectFrom(qAttendance).where(qAttendance.contentId.eq(contentId)).fetchFirst();
    }

    public Attendance findAttendanceByQuizId(long quizId){

        if(queryFactory == null){
            queryFactory = new JPAQueryFactory(entityManager);
        }

        QAttendance qAttendance = QAttendance.attendance;

        return queryFactory.selectFrom(qAttendance).where(qAttendance.quizId.eq(quizId)).fetchFirst();
    }

    public List<Attendance> findAttendancesOrderByCourseId(){

        if(queryFactory == null){
            queryFactory = new JPAQueryFactory(entityManager);
        }

        QAttendance qAttendance = QAttendance.attendance;

        return queryFactory.selectFrom(qAttendance).orderBy(qAttendance.courseId.asc()).fetch();
    }

    public List<Attendance> findAttendancesOrderByUnitId(){

        if(queryFactory == null){
            queryFactory = new JPAQueryFactory(entityManager);
        }

        QAttendance qAttendance = QAttendance.attendance;

        return queryFactory.selectFrom(qAttendance).orderBy(qAttendance.unitId.asc()).fetch();
    }

    public List<Attendance> findAttendancesOrderByContentId(){

        if(queryFactory == null){
            queryFactory = new JPAQueryFactory(entityManager);
        }

        QAttendance qAttendance = QAttendance.attendance;

        return queryFactory.selectFrom(qAttendance).orderBy(qAttendance.contentId.asc()).fetch();
    }

    public List<Attendance> findAttendancesOrderByQuizId(){

        if(queryFactory == null){
            queryFactory = new JPAQueryFactory(entityManager);
        }

        QAttendance qAttendance = QAttendance.attendance;

        return queryFactory.selectFrom(qAttendance).orderBy(qAttendance.quizId.asc()).fetch();
    }

    public List<Attendance> findAttendancesByCourseId(long courseId){

        if(queryFactory == null){
            queryFactory = new JPAQueryFactory(entityManager);
        }

        QAttendance qAttendance = QAttendance.attendance;
        return queryFactory.selectFrom(qAttendance).where(qAttendance.courseId.eq(courseId)).fetch();
    }

    public List<Attendance> findAttendancesByUnitId(long unitId){

        if(queryFactory == null){
            queryFactory = new JPAQueryFactory(entityManager);
        }

        QAttendance qAttendance = QAttendance.attendance;
        return queryFactory.selectFrom(qAttendance).where(qAttendance.unitId.eq(unitId)).fetch();
    }

    public List<Attendance> findAttendancesByContentId(long contentId){

        if(queryFactory == null){
            queryFactory = new JPAQueryFactory(entityManager);
        }

        QAttendance qAttendance = QAttendance.attendance;
        return queryFactory.selectFrom(qAttendance).where(qAttendance.contentId.eq(contentId)).fetch();
    }

    public List<Attendance> findAttendancesByQuizId(long quizId){

        if(queryFactory == null){
            queryFactory = new JPAQueryFactory(entityManager);
        }

        QAttendance qAttendance = QAttendance.attendance;
        return queryFactory.selectFrom(qAttendance).where(qAttendance.quizId.eq(quizId)).fetch();
    }

    public List<Attendance> findAttendancesByUsername(String username){

        if(queryFactory == null){
            queryFactory = new JPAQueryFactory(entityManager);
        }

        QAttendance qAttendance = QAttendance.attendance;
        return queryFactory.selectFrom(qAttendance).where(qAttendance.username.eq(username)).fetch();
    }

    public Attendance save(Attendance attendance){

        if(queryFactory == null){
            queryFactory = new JPAQueryFactory(entityManager);
        }

        QAttendance qAttendance = QAttendance.attendance;

//        Attendance fAttendance = queryFactory.selectFrom(qAttendance).where(qAttendance.id.eq(attendance.getId())).fetchFirst();
        if(attendance.getId() == null){

            queryFactory.insert(qAttendance)
                    .columns(qAttendance.attendanceId, qAttendance.courseId, qAttendance.unitId, qAttendance.contentId, qAttendance.quizId, qAttendance.username, qAttendance.attendanceDate, qAttendance.team, qAttendance.role, qAttendance.recordScore, qAttendance.recordShot, qAttendance.score, qAttendance.longDate, qAttendance.state)
                    .values(attendance.getAttendanceId(), attendance.getCourseId(), attendance.getUnitId(), attendance.getContentId(), attendance.getQuizId(), attendance.getUsername(), attendance.getAttendanceDate(), attendance.getTeam(), attendance.getRole(), attendance.getRecordScore(), attendance.getRecordShot(), attendance.getScore(), attendance.getLongDate(), attendance.getState())
                    .execute();

        }else{

            queryFactory.update(qAttendance)
                    .set(qAttendance.attendanceId, attendance.getAttendanceId())
                    .set(qAttendance.courseId, attendance.getCourseId())
                    .set(qAttendance.unitId, attendance.getUnitId())
                    .set(qAttendance.contentId, attendance.getContentId())
                    .set(qAttendance.quizId, attendance.getQuizId())
                    .set(qAttendance.username, attendance.getUsername())
                    .set(qAttendance.attendanceDate, attendance.getAttendanceDate())
                    .set(qAttendance.team, attendance.getTeam())
                    .set(qAttendance.role, attendance.getRole())
                    .set(qAttendance.recordScore, attendance.getRecordScore())
                    .set(qAttendance.recordShot, attendance.getRecordShot())
                    .set(qAttendance.score, attendance.getScore())
                    .set(qAttendance.longDate, attendance.getLongDate())
                    .set(qAttendance.state, attendance.getState())
                    .where(qAttendance.id.eq(attendance.getId()))
                    .execute();
        }

        return queryFactory.selectFrom(qAttendance).where(qAttendance.attendanceId.eq(attendance.getAttendanceId())).fetchFirst();
    }

    public List<Attendance> delete(long id, String username){
        QAttendance qContent = QAttendance.attendance;

        queryFactory.delete(qContent)
                .where(qContent.id.eq(id))
                .execute();

        return findAttendancesByUsername(username);
    }

}
