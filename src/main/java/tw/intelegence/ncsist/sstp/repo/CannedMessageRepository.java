package tw.intelegence.ncsist.sstp.repo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tw.intelegence.ncsist.sstp.bean.CannedMessage;
import tw.intelegence.ncsist.sstp.bean.QCannedMessage;

import java.util.List;

@Repository
public class CannedMessageRepository {
    @Autowired
    private EntityManager entityManager;
    private JPAQueryFactory queryFactory;

    public Long findCannedId(){

        if(queryFactory == null){
            queryFactory = new JPAQueryFactory(entityManager);
        }

        QCannedMessage qCannedMessage = QCannedMessage.cannedMessage;
        CannedMessage cannedMessage = queryFactory.selectFrom(qCannedMessage).orderBy(qCannedMessage.cannedId.desc()).fetchFirst();

        return cannedMessage.getCannedId();
    }

    public CannedMessage findCannedMessageByCannedId(long cannedId){

        if(queryFactory == null){
            queryFactory = new JPAQueryFactory(entityManager);
        }

        QCannedMessage qCannedMessage = QCannedMessage.cannedMessage;

        return queryFactory.selectFrom(qCannedMessage).where(qCannedMessage.cannedId.eq(cannedId)).fetchFirst();
    }

    public List<CannedMessage> findAllCannedMessagesOrderByMessageLevelAndCannedId(){

        if(queryFactory == null){
            queryFactory = new JPAQueryFactory(entityManager);
        }

        QCannedMessage qCannedMessage = QCannedMessage.cannedMessage;

        return queryFactory.selectFrom(qCannedMessage).orderBy(qCannedMessage.messageLevel.asc(), qCannedMessage.cannedId.asc()).fetch();
    }

    public List<CannedMessage> findCannedMessagesByMessageLevelOrderByMessageLevelAndCannedId(long messageLevel){

        if(queryFactory == null){
            queryFactory = new JPAQueryFactory(entityManager);
        }

        QCannedMessage qCannedMessage = QCannedMessage.cannedMessage;

        return queryFactory.selectFrom(qCannedMessage).where(qCannedMessage.messageLevel.eq(messageLevel)).orderBy(qCannedMessage.messageLevel.asc(), qCannedMessage.cannedId.asc()).fetch();
    }

    public CannedMessage save(CannedMessage cannedMessage){

        if(queryFactory == null){
            queryFactory = new JPAQueryFactory(entityManager);
        }

        QCannedMessage qCannedMessage = QCannedMessage.cannedMessage;

//        CannedMessage fCannedMessage = queryFactory.selectFrom(qCannedMessage).where(qCannedMessage.cannedId.eq(cannedMessage.getCannedId())).fetchFirst();

        if(cannedMessage.getId() == null){

            queryFactory.insert(qCannedMessage)
                    .columns(qCannedMessage.cannedId, qCannedMessage.messageLevel, qCannedMessage.message, qCannedMessage.longDate, qCannedMessage.state)
                    .values(cannedMessage.getCannedId(), cannedMessage.getMessageLevel(), cannedMessage.getMessage(), cannedMessage.getLongDate(), cannedMessage.getState())
                    .execute();

            return queryFactory.selectFrom(qCannedMessage).orderBy(qCannedMessage.cannedId.desc()).fetchFirst();

        }else{

            queryFactory.update(qCannedMessage)
                    .set(qCannedMessage.cannedId, cannedMessage.getCannedId())
                    .set(qCannedMessage.messageLevel, cannedMessage.getMessageLevel())
                    .set(qCannedMessage.message, cannedMessage.getMessage())
                    .set(qCannedMessage.longDate, cannedMessage.getLongDate())
                    .set(qCannedMessage.state, cannedMessage.getState())
                    .where(qCannedMessage.id.eq(cannedMessage.getId()))
                    .execute();

            return queryFactory.selectFrom(qCannedMessage).where(qCannedMessage.id.eq(cannedMessage.getId())).fetchFirst();
        }

    }

    public List<CannedMessage> delete(long id, long messageLevel){

        if(queryFactory == null){
            queryFactory = new JPAQueryFactory(entityManager);
        }

        QCannedMessage qCannedMessage = QCannedMessage.cannedMessage;

        queryFactory.delete(qCannedMessage)
                .where(qCannedMessage.id.eq(id))
                .execute();

        return findCannedMessagesByMessageLevelOrderByMessageLevelAndCannedId(messageLevel);
    }

}
