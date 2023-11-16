package tw.intelegence.ncsist.sstp.repo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tw.intelegence.ncsist.sstp.bean.QTip;
import tw.intelegence.ncsist.sstp.bean.Tip;

import java.util.List;

@Repository
public class TipRepository {
//    @Autowired
//    private EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    public TipRepository(EntityManager entityManager){
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public Long findTipId(){

//        if(queryFactory == null){
//            queryFactory = new JPAQueryFactory(entityManager);
//        }

        QTip qTip = QTip.tip;
        Tip tip = queryFactory.selectFrom(qTip).orderBy(qTip.unitId.desc()).fetchFirst();

        return tip.getContentId();
    }

    public Long findTipIdByUnitId(long unitId){

//        if(queryFactory == null){
//            queryFactory = new JPAQueryFactory(entityManager);
//        }

        QTip qTip = QTip.tip;

        Tip tip = queryFactory.selectFrom(qTip).where(qTip.unitId.eq(unitId)).orderBy(qTip.tipId.desc()).fetchFirst();

        long tipId = 0L;

        if(tip != null){
            tipId = tip.getTipId();
        }

        return tipId;
    }

    public Long findTipIdByContentId(long contentId){

//        if(queryFactory == null){
//            queryFactory = new JPAQueryFactory(entityManager);
//        }

        QTip qTip = QTip.tip;

        Tip tip = queryFactory.selectFrom(qTip).where(qTip.contentId.eq(contentId)).orderBy(qTip.tipId.desc()).fetchFirst();

        long tipId = 0L;
        if(tip != null){
            tipId = tip.getTipId();
        }

        return tipId;
    }

    public Tip findTipByUnitId(long unitId){

//        if(queryFactory == null){
//            queryFactory = new JPAQueryFactory(entityManager);
//        }

        QTip qTip = QTip.tip;

        return queryFactory.selectFrom(qTip).where(qTip.unitId.eq(unitId)).fetchFirst();
    }

    public Tip findTipByContentId(long contentId){

//        if(queryFactory == null){
//            queryFactory = new JPAQueryFactory(entityManager);
//        }

        QTip qTip = QTip.tip;

        return queryFactory.selectFrom(qTip).where(qTip.contentId.eq(contentId)).fetchFirst();
    }

    public List<Tip> findTipsOrderByContentId(){

//        if(queryFactory == null){
//            queryFactory = new JPAQueryFactory(entityManager);
//        }

        QTip qTip = QTip.tip;

        return queryFactory.selectFrom(qTip).orderBy(qTip.contentId.asc()).fetch();
    }

    public List<Tip> findTipsByUnitIdOrderByContentId(long unitId){

//        if(queryFactory == null){
//            queryFactory = new JPAQueryFactory(entityManager);
//        }

        QTip qTip = QTip.tip;

        return queryFactory.selectFrom(qTip).where(qTip.unitId.eq(unitId)).orderBy(qTip.contentId.asc()).fetch();
    }

    public Tip save(Tip tip){

//        if(queryFactory == null){
//            queryFactory = new JPAQueryFactory(entityManager);
//        }

        QTip qTip = QTip.tip;

//        Tip fAttendance = queryFactory.selectFrom(qTip).where(qTip.id.eq(tip.getId())).fetchFirst();
        if(tip.getId() == null){

            queryFactory.insert(qTip)
                    .columns(qTip.tipId, qTip.unitId, qTip.contentId, qTip.title, qTip.content, qTip.longDate, qTip.state)
                    .values(tip.getTipId(), tip.getUnitId(), tip.getContentId(), tip.getTitle(), tip.getContent(), tip.getLongDate(), tip.getState())
                    .execute();

        }else{

            queryFactory.update(qTip)
                    .set(qTip.tipId, tip.getTipId())
                    .set(qTip.unitId, tip.getUnitId())
                    .set(qTip.contentId, tip.getContentId())
                    .set(qTip.title, tip.getTitle())
                    .set(qTip.content, tip.getContent())
                    .set(qTip.longDate, tip.getLongDate())
                    .set(qTip.state, tip.getState())
                    .where(qTip.id.eq(tip.getId()))
                    .execute();
        }

        return queryFactory.selectFrom(qTip).where(qTip.tipId.eq(tip.getTipId())).fetchFirst();
    }

    public List<Tip> delete(long id, long unitId){

//        if(queryFactory == null){
//            queryFactory = new JPAQueryFactory(entityManager);
//        }

        QTip qTip = QTip.tip;

        queryFactory.delete(qTip)
                .where(qTip.id.eq(id))
                .execute();

        return findTipsByUnitIdOrderByContentId(unitId);
    }

}
