package tw.intelegence.ncsist.sstp.repo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tw.intelegence.ncsist.sstp.bean.Quiz;
import tw.intelegence.ncsist.sstp.bean.QQuiz;

import java.util.List;

@Repository
public class QuizRepository {
    @Autowired
    private EntityManager entityManager;
    private JPAQueryFactory queryFactory;

    public Long findByQuizId(){

        if(queryFactory == null){
            queryFactory = new JPAQueryFactory(entityManager);
        }

        QQuiz qQuiz = QQuiz.quiz;
        Quiz quiz = queryFactory.selectFrom(qQuiz).orderBy(qQuiz.quizId.desc()).fetchFirst();
        return quiz.getQuizId();
    }


    public Quiz findQuizByQuizId(long quizId){

        if(queryFactory == null){
            queryFactory = new JPAQueryFactory(entityManager);
        }

        QQuiz qQuiz = QQuiz.quiz;

        return queryFactory.selectFrom(qQuiz).where(qQuiz.quizId.eq(quizId)).fetchFirst();
    }

    public List<Quiz> findAllByUnitIdOrderByQuizId(){

        if(queryFactory == null){
            queryFactory = new JPAQueryFactory(entityManager);
        }

        QQuiz qQuiz = QQuiz.quiz;
        return queryFactory.selectFrom(qQuiz).orderBy(qQuiz.quizId.asc()).fetch();
    }

    public List<Quiz> findQuizsByUnitIdOrderByQuizId(long unitId){

        if(queryFactory == null){
            queryFactory = new JPAQueryFactory(entityManager);
        }

        QQuiz qQuiz = QQuiz.quiz;
        return queryFactory.selectFrom(qQuiz).where(qQuiz.unitId.eq(unitId)).orderBy(qQuiz.quizId.asc()).fetch();
    }

    public Quiz save(Quiz quiz){

        if(queryFactory == null){
            queryFactory = new JPAQueryFactory(entityManager);
        }

        QQuiz qQuiz = QQuiz.quiz;

//        Quiz fQuiz = queryFactory.selectFrom(qQuiz).where(qQuiz.id.eq(quiz.getId()).and(qQuiz.quizId.eq(quiz.getQuizId()))).fetchFirst();
        if(quiz.getId() == null){

            queryFactory.insert(qQuiz)
                    .columns(qQuiz.unitId, qQuiz.quizId, qQuiz.title, qQuiz.content, qQuiz.tofQuiz, qQuiz.essayQuiz, qQuiz.answer, qQuiz.longDate, qQuiz.state)
                    .values(quiz.getUnitId(), quiz.getQuizId(), quiz.getTitle(), quiz.getContent(), quiz.getTofQuiz(), quiz.getEssayQuiz(), quiz.getAnswer(), quiz.getLongDate(), quiz.getState())
                    .execute();

        }else{

            queryFactory.update(qQuiz)
                    .set(qQuiz.unitId, quiz.getUnitId())
                    .set(qQuiz.quizId, quiz.getQuizId())
                    .set(qQuiz.title, quiz.getTitle())
                    .set(qQuiz.content, quiz.getContent())
                    .set(qQuiz.tofQuiz, quiz.getTofQuiz())
                    .set(qQuiz.essayQuiz, quiz.getEssayQuiz())
                    .set(qQuiz.answer, quiz.getAnswer())
                    .set(qQuiz.longDate, quiz.getLongDate())
                    .set(qQuiz.state, quiz.getState())
                    .where(qQuiz.id.eq(quiz.getId()).and(qQuiz.quizId.eq(quiz.getQuizId())))
                    .execute();
        }

        return queryFactory.selectFrom(qQuiz).where(qQuiz.quizId.eq(quiz.getQuizId())).fetchFirst();
    }

    public List<Quiz> delete(Quiz quiz){

        if(queryFactory == null){
            queryFactory = new JPAQueryFactory(entityManager);
        }

        QQuiz qQuiz = QQuiz.quiz;

        queryFactory.delete(qQuiz)
                .where(qQuiz.id.eq(quiz.getId()))
                .execute();

        List<Quiz> QuizList;

        if(quiz.getUnitId() == 0){
            QuizList = findAllByUnitIdOrderByQuizId();
        }else{
            QuizList = findQuizsByUnitIdOrderByQuizId(quiz.getUnitId());
        }

        return QuizList;
    }


}
