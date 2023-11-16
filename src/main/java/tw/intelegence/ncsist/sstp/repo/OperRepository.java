package tw.intelegence.ncsist.sstp.repo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tw.intelegence.ncsist.sstp.bean.Oper;
import tw.intelegence.ncsist.sstp.bean.QOper;

import java.util.List;

@Repository
public class OperRepository {
//    @Autowired
//    private EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public OperRepository(EntityManager entityManager){
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public Long findOperId(){

//        if(queryFactory == null){
//            queryFactory = new JPAQueryFactory(entityManager);
//        }

        QOper qOper = QOper.oper;
        Oper oper = queryFactory.selectFrom(qOper).orderBy(qOper.operId.desc()).fetchFirst();

        return oper.getOperId();
    }

    public Oper findOperByUnitId(long unitId){

//        if(queryFactory == null){
//            queryFactory = new JPAQueryFactory(entityManager);
//        }

        QOper qOper = QOper.oper;

        return queryFactory.selectFrom(qOper).where(qOper.unitId.eq(unitId)).fetchFirst();
    }

    public List<Oper> findAllOpersOrderByContentId(){

//        if(queryFactory == null){
//            queryFactory = new JPAQueryFactory(entityManager);
//        }

        QOper qOper = QOper.oper;

        return queryFactory.selectFrom(qOper).orderBy(qOper.contentId.asc()).fetch();
    }

    public List<Oper> findOpersByUnitIdOrderByContentId(long unitId){

//        if(queryFactory == null){
//            queryFactory = new JPAQueryFactory(entityManager);
//        }

        QOper qOper = QOper.oper;

        return queryFactory.selectFrom(qOper).where(qOper.unitId.eq(unitId)).orderBy(qOper.contentId.asc()).fetch();
    }

    public Oper save(Oper oper){

//        if(queryFactory == null){
//            queryFactory = new JPAQueryFactory(entityManager);
//        }

        QOper qOper = QOper.oper;

//        Oper fOper = queryFactory.selectFrom(qOper).where(qOper.operId.eq(oper.getOperId())).fetchFirst();
        if(oper.getId() == null){

            queryFactory.insert(qOper)
                    .columns(qOper.operId, qOper.unitId, qOper.contentId, qOper.title, qOper.titleCh, qOper.pictureName, qOper.answer, qOper.group1, qOper.group2, qOper.group3, qOper.group4, qOper.longDate, qOper.state)
                    .values(oper.getOperId(), oper.getUnitId(), oper.getContentId(), oper.getTitle(), oper.getTitleCh(), oper.getPictureName(), oper.getAnswer(), oper.getGroup1(), oper.getGroup2(), oper.getGroup3(), oper.getGroup4(), oper.getLongDate(), oper.getState())
                    .execute();

            return queryFactory.selectFrom(qOper).orderBy(qOper.operId.desc()).fetchFirst();

        }else{

            queryFactory.update(qOper)
                    .set(qOper.operId, oper.getOperId())
                    .set(qOper.unitId, oper.getUnitId())
                    .set(qOper.contentId, oper.getContentId())
                    .set(qOper.title, oper.getTitle())
                    .set(qOper.titleCh, oper.getTitleCh())
                    .set(qOper.pictureName, oper.getPictureName())
                    .set(qOper.answer, oper.getAnswer())
                    .set(qOper.group1, oper.getGroup1())
                    .set(qOper.group2, oper.getGroup2())
                    .set(qOper.group3, oper.getGroup3())
                    .set(qOper.group4, oper.getGroup4())
                    .set(qOper.longDate, oper.getLongDate())
                    .set(qOper.state, oper.getState())
                    .where(qOper.id.eq(oper.getId()))
                    .execute();

//            return queryFactory.selectFrom(qOper).where(qOper.id.eq(oper.getId())).fetchFirst();
            return queryFactory.selectFrom(qOper).where(qOper.operId.eq(oper.getOperId())).fetchFirst();
        }

    }

    public List<Oper> delete(long id, long unitId){

//        if(queryFactory == null){
//            queryFactory = new JPAQueryFactory(entityManager);
//        }

        QOper qOper = QOper.oper;

        queryFactory.delete(qOper)
                .where(qOper.id.eq(id))
                .execute();

        return findOpersByUnitIdOrderByContentId(unitId);
    }

}
