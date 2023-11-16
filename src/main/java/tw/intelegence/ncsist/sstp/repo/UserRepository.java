package tw.intelegence.ncsist.sstp.repo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tw.intelegence.ncsist.sstp.bean.QUser;
import tw.intelegence.ncsist.sstp.bean.User;


import jakarta.persistence.EntityManager;
import tw.intelegence.ncsist.sstp.model.UserDTO;

import java.util.List;

@Repository
public class UserRepository {

//    @Autowired
//    private EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public UserRepository(EntityManager entityManager){
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public User findUserByUsernameAndPassword(String username, String password) {
//        if(queryFactory == null){
//            queryFactory = new JPAQueryFactory(entityManager);
//        }

        QUser user = QUser.user;
        return queryFactory.selectFrom(user)
                .where(user.username.eq(username).and(user.password.eq(password)))
                .fetchFirst();
    }

    public User addUser(User user){
//        if(queryFactory == null){
//            queryFactory = new JPAQueryFactory(entityManager);
//        }

        QUser qUser = QUser.user;
        queryFactory.insert(qUser)
                .columns(qUser.username, qUser.password, qUser.ip, qUser.level, qUser.name, qUser.state, qUser.longDate, qUser.studentId, qUser.studentBatch)
                .values(user.getUsername(), user.getPassword(), user.getIp(), user.getLevel(), user.getName(), user.getState(), user.getLongDate(), user.getStudentId(), user.getStudentBatch())
                .execute();

        return findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    public User savePasswordByUsername(UserDTO userDTO){
//        if(queryFactory == null){
//            queryFactory = new JPAQueryFactory(entityManager);
//        }

        QUser qUser = QUser.user;
        long success = queryFactory.update(qUser)
                .set(qUser.password, userDTO.getPassword())
                .where(qUser.username.eq(userDTO.getUsername()))
                .execute();
        return findUserByUsernameAndPassword(userDTO.getUsername(), userDTO.getPassword());
    }

    public User saveUser(User user){
//        if(queryFactory == null){
//            queryFactory = new JPAQueryFactory(entityManager);
//        }

        QUser qUser = QUser.user;
        User fUser = queryFactory.selectFrom(qUser).where(qUser.username.eq(user.getUsername())).fetchFirst();

        if(fUser == null){
            queryFactory.insert(qUser)
                    .columns(qUser.username, qUser.password, qUser.ip, qUser.level, qUser.grade, qUser.studentWork, qUser.studentUnit, qUser.studentUnitCode, qUser.name, qUser.state, qUser.longDate, qUser.studentId, qUser.studentBatch)
                    .values(user.getUsername(), user.getPassword(), user.getIp(), user.getLevel(), user.getGrade(), user.getStudentWork(), user.getStudentUnit(), user.getStudentUnitCode(), user.getName(), user.getState(), user.getLongDate(), user.getStudentId(), user.getStudentBatch())
                    .execute();
        }else{
            queryFactory.update(qUser)
                    .set(qUser.password, user.getPassword())
                    .set(qUser.name, user.getName())
                    .set(qUser.studentId, user.getStudentId())
                    .set(qUser.ip, user.getIp())
                    .set(qUser.level, user.getLevel())
                    .set(qUser.grade, user.getGrade())
                    .set(qUser.studentWork, qUser.studentWork)
                    .set(qUser.studentUnit, user.getStudentUnit())
                    .set(qUser.studentUnitCode, user.getStudentUnitCode())
                    .set(qUser.longDate, user.getLongDate())
                    .set(qUser.oAuthKey, user.getOAuthKey())
                    .set(qUser.studentBatch, user.getStudentBatch())
                    .where(qUser.username.eq(user.getUsername()))
                    .execute();
        }

        return queryFactory.selectFrom(qUser).where(qUser.username.eq(user.getUsername())).fetchFirst();
    }

    public User deleteUser(UserDTO userDTO){
//        if(queryFactory == null){
//            queryFactory = new JPAQueryFactory(entityManager);
//        }

        QUser qUser = QUser.user;
        long success = queryFactory.delete(qUser)
                .where(qUser.username.eq(userDTO.getUsername()))
                .execute();
        return findUserByUsernameAndPassword(userDTO.getUsername(), userDTO.getPassword());
    }

    public long deleteUser(String username){
//        if(queryFactory == null){
//            queryFactory = new JPAQueryFactory(entityManager);
//        }

        QUser qUser = QUser.user;

        return queryFactory.delete(qUser)
                .where(qUser.username.eq(username))
                .execute();
    }

    public List<User> findAllUsers(){
//        if(queryFactory == null){
//            queryFactory = new JPAQueryFactory(entityManager);
//        }

        QUser qUser = QUser.user;
        return queryFactory.selectFrom(qUser).fetch();
    }
}