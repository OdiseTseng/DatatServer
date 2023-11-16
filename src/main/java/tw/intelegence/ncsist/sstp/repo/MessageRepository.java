package tw.intelegence.ncsist.sstp.repo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tw.intelegence.ncsist.sstp.bean.Message;
import tw.intelegence.ncsist.sstp.bean.QMessage;

import java.util.List;

@Repository
public class MessageRepository {
//    @Autowired
//    private EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public MessageRepository(EntityManager entityManager){
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public Long findCannedId(){

//        if(queryFactory == null){
//            queryFactory = new JPAQueryFactory(entityManager);
//        }


        QMessage qMessage = QMessage.message1;
        Message message = queryFactory.selectFrom(qMessage).orderBy(qMessage.cannedId.desc()).fetchFirst();

        return message.getCannedId();
    }

    public Message findMessageByCannedId(long cannedId){

//        if(queryFactory == null){
//            queryFactory = new JPAQueryFactory(entityManager);
//        }

        QMessage qMessage = QMessage.message1;

        return queryFactory.selectFrom(qMessage).where(qMessage.cannedId.eq(cannedId)).fetchFirst();
    }

    public List<Message> findAllMessagesOrderByMessageLevelAndCannedId(){

//        if(queryFactory == null){
//            queryFactory = new JPAQueryFactory(entityManager);
//        }

        QMessage qMessage = QMessage.message1;

        return queryFactory.selectFrom(qMessage).orderBy(qMessage.messageLevel.asc(), qMessage.cannedId.asc()).fetch();
    }

    public List<Message> findMessagesByMessageLevelOrderByMessageLevelAndCannedId(long messageLevel){

//        if(queryFactory == null){
//            queryFactory = new JPAQueryFactory(entityManager);
//        }

        QMessage qMessage = QMessage.message1;

        return queryFactory.selectFrom(qMessage).where(qMessage.messageLevel.eq(messageLevel)).orderBy(qMessage.messageLevel.asc(), qMessage.cannedId.asc()).fetch();
    }

    public Message save(Message message){

//        if(queryFactory == null){
//            queryFactory = new JPAQueryFactory(entityManager);
//        }

        QMessage qMessage = QMessage.message1;

//        Message fMessage = queryFactory.selectFrom(qMessage).where(qMessage.cannedId.eq(Message.getCannedId())).fetchFirst();

        if(message.getId() == null){

            queryFactory.insert(qMessage)
                    .columns(qMessage.cannedId, qMessage.messageLevel, qMessage.message, qMessage.longDate, qMessage.state)
                    .values(message.getCannedId(), message.getMessageLevel(), message.getMessage(), message.getLongDate(), message.getState())
                    .execute();

            return queryFactory.selectFrom(qMessage).orderBy(qMessage.cannedId.desc()).fetchFirst();

        }else{

            queryFactory.update(qMessage)
                    .set(qMessage.cannedId, message.getCannedId())
                    .set(qMessage.messageLevel, message.getMessageLevel())
                    .set(qMessage.message, message.getMessage())
                    .set(qMessage.longDate, message.getLongDate())
                    .set(qMessage.state, message.getState())
                    .where(qMessage.id.eq(message.getId()))
                    .execute();

            return queryFactory.selectFrom(qMessage).where(qMessage.id.eq(message.getId())).fetchFirst();
        }

    }

    public List<Message> delete(long id, long messageLevel){

//        if(queryFactory == null){
//            queryFactory = new JPAQueryFactory(entityManager);
//        }

        QMessage qMessage = QMessage.message1;

        queryFactory.delete(qMessage)
                .where(qMessage.id.eq(id))
                .execute();

        return findMessagesByMessageLevelOrderByMessageLevelAndCannedId(messageLevel);
    }

}
