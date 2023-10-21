package tw.intelegence.ncsist.sstp.repo;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tw.intelegence.ncsist.sstp.bean.QUnit;
import tw.intelegence.ncsist.sstp.bean.Unit;

import java.util.List;

@Repository
public class UnitRepository {
    @Autowired
    private EntityManager entityManager;
    private JPAQueryFactory queryFactory;

    public Long findByUnitId(){

        if(queryFactory == null){
            queryFactory = new JPAQueryFactory(entityManager);
        }

        QUnit qUnit = QUnit.unit;

        Unit unit = queryFactory.selectFrom(qUnit).orderBy(qUnit.courseId.desc(), qUnit.unitId.desc()).fetchFirst();


        return unit.getUnitId();
    }


    public Unit findUnitByUnitId(long unitId){

        if(queryFactory == null){
            queryFactory = new JPAQueryFactory(entityManager);
        }

        QUnit qUnit = QUnit.unit;

        return queryFactory.selectFrom(qUnit).where(qUnit.unitId.eq(unitId)).fetchFirst();
    }

    public List<Unit> findAllOrderByUnitId(){

        if(queryFactory == null){
            queryFactory = new JPAQueryFactory(entityManager);
        }

        QUnit qUnit = QUnit.unit;

//        OrderSpecifier<Long> orderASC = new OrderSpecifier<>(Order.ASC, qUnit.unitId);

        return queryFactory.selectFrom(qUnit).orderBy(qUnit.unitId.asc()).fetch();
    }

    public List<Unit> findByCourseIdOrderByUnitId(long courseId){

        if(queryFactory == null){
            queryFactory = new JPAQueryFactory(entityManager);
        }

        QUnit qUnit = QUnit.unit;
        return queryFactory.selectFrom(qUnit).where(qUnit.courseId.eq(courseId)).orderBy(qUnit.unitId.asc()).fetch();
    }

    public Unit save(Unit unit){

        if(queryFactory == null){
            queryFactory = new JPAQueryFactory(entityManager);
        }

        QUnit qUnit = QUnit.unit;

//        Unit fUnit = queryFactory.selectFrom(qUnit).where(qUnit.id.eq(unit.getId()).and(qUnit.unitId.eq(unit.getUnitId()))).fetchFirst();
        if(unit.getId() == null){

            queryFactory.insert(qUnit)
                    .columns(qUnit.courseId, qUnit.unitId, qUnit.unitName, qUnit.unitSchedule, qUnit.unitSubject, qUnit.unitOrder, qUnit.descTitle1, qUnit.descContent1, qUnit.descTitle2, qUnit.descContent2, qUnit.descTitle3, qUnit.descContent3, qUnit.videoUrl, qUnit.videoFormat, qUnit.videoContents, qUnit.dfcsFilename, qUnit.creditUnits, qUnit.pictureUrl1, qUnit.pictureUrl2, qUnit.pictureUrl3, qUnit.pictureUrl4, qUnit.contentList, qUnit.longDate, qUnit.state)
                    .values(unit.getCourseId(), unit.getUnitId(), unit.getUnitName(), unit.getUnitSchedule(), unit.getUnitSubject(), unit.getUnitOrder(), unit.getDescTitle1(), unit.getDescContent1(), unit.getDescTitle2(), unit.getDescContent2(), unit.getDescTitle3(), unit.getDescContent3(), unit.getVideoUrl(), unit.getVideoFormat(), unit.getVideoContents(), unit.getDfcsFilename(), unit.getCreditUnits(), unit.getPictureUrl1(), unit.getPictureUrl2(), unit.getPictureUrl3(), unit.getPictureUrl4(), unit.getContentList(), unit.getLongDate(), unit.getState())
                    .execute();

        }else{

            queryFactory.update(qUnit)
                    .set(qUnit.courseId, unit.getCourseId())
                    .set(qUnit.unitId, unit.getUnitId())
                    .set(qUnit.unitName, unit.getUnitName())
                    .set(qUnit.unitSchedule, unit.getUnitSchedule())
                    .set(qUnit.unitSubject, unit.getUnitSubject())
                    .set(qUnit.unitOrder, unit.getUnitOrder())
                    .set(qUnit.descTitle1, unit.getDescTitle1())
                    .set(qUnit.descContent1, unit.getDescContent1())
                    .set(qUnit.descTitle2, unit.getDescTitle2())
                    .set(qUnit.descContent2, unit.getDescContent2())
                    .set(qUnit.descTitle3, unit.getDescTitle3())
                    .set(qUnit.descContent3, unit.getDescContent3())
                    .set(qUnit.videoUrl, unit.getVideoUrl())
                    .set(qUnit.videoFormat, unit.getVideoFormat())
                    .set(qUnit.videoContents, unit.getVideoContents())
                    .set(qUnit.dfcsFilename, unit.getDfcsFilename())
                    .set(qUnit.creditUnits, unit.getCreditUnits())
                    .set(qUnit.pictureUrl1, unit.getPictureUrl1())
                    .set(qUnit.pictureUrl2, unit.getPictureUrl2())
                    .set(qUnit.pictureUrl3, unit.getPictureUrl3())
                    .set(qUnit.pictureUrl4, unit.getPictureUrl4())
                    .set(qUnit.contentList, unit.getContentList())
                    .set(qUnit.longDate, unit.getLongDate())
                    .set(qUnit.state, unit.getState())
                    .where(qUnit.id.eq(unit.getId()).and(qUnit.unitId.eq(unit.getUnitId())))
                    .execute();
        }

        return queryFactory.selectFrom(qUnit).where(qUnit.unitId.eq(unit.getUnitId())).fetchFirst();
    }

    public List<Unit> delete(Unit unit){

        if(queryFactory == null){
            queryFactory = new JPAQueryFactory(entityManager);
        }

        QUnit qUnit = QUnit.unit;

        queryFactory.delete(qUnit)
                .where(qUnit.id.eq(unit.getId()))
                .execute();

        List<Unit> unitList;

        if(unit.getUnitId() == 0){
            unitList = findAllOrderByUnitId();
        }else{
            unitList = findByCourseIdOrderByUnitId(unit.getCourseId());
        }

        return unitList;
    }


}
