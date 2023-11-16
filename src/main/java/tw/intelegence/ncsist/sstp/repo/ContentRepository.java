package tw.intelegence.ncsist.sstp.repo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tw.intelegence.ncsist.sstp.bean.Content;
import tw.intelegence.ncsist.sstp.bean.QContent;

import java.util.List;

@Repository
public class ContentRepository {
//    @Autowired
//    private EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public  ContentRepository(EntityManager entityManager){
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public Long findContentId(){

//        if(queryFactory == null){
//            queryFactory = new JPAQueryFactory(entityManager);
//        }

        QContent qContent = QContent.content1;
        Content content = queryFactory.selectFrom(qContent).orderBy(qContent.contentId.desc()).fetchFirst();

        return content.getContentId();
    }

    public Long findContentIdByUnitId(long unitId){

//        if(queryFactory == null){
//            queryFactory = new JPAQueryFactory(entityManager);
//        }

        QContent qContent = QContent.content1;

        Content content = queryFactory.selectFrom(qContent).where(qContent.unitId.eq(unitId)).orderBy(qContent.contentId.desc()).fetchFirst();

        long contentId = 0L;
        if(content != null){
            contentId = content.getContentId();
        }
        return contentId;
    }


    public Content findContentByContentId(long contentId){

//        if(queryFactory == null){
//            queryFactory = new JPAQueryFactory(entityManager);
//        }

        QContent qContent = QContent.content1;

        return queryFactory.selectFrom(qContent).where(qContent.contentId.eq(contentId)).fetchFirst();
    }

    public List<Content> findContentsOrderByContentId(){

//        if(queryFactory == null){
//            queryFactory = new JPAQueryFactory(entityManager);
//        }

        QContent qContent = QContent.content1;
        return queryFactory.selectFrom(qContent).orderBy(qContent.contentId.asc()).fetch();
    }

    public List<Content> findContentsByUnitIdOrderByContentId(long unitId){

//        if(queryFactory == null){
//            queryFactory = new JPAQueryFactory(entityManager);
//        }

        QContent qContent = QContent.content1;
        return queryFactory.selectFrom(qContent).where(qContent.unitId.eq(unitId)).orderBy(qContent.contentId.asc()).fetch();
    }

    public List<Content> findContentsByUnitIdOrderByContentOrder(long unitId){

//        if(queryFactory == null){
//            queryFactory = new JPAQueryFactory(entityManager);
//        }

        QContent qContent = QContent.content1;
        return queryFactory.selectFrom(qContent).where(qContent.unitId.eq(unitId)).orderBy(qContent.contentOrder.asc()).fetch();
    }

    public Content save(Content content){

//        if(queryFactory == null){
//            queryFactory = new JPAQueryFactory(entityManager);
//        }

        QContent qContent = QContent.content1;

//        Content fContent = queryFactory.selectFrom(qContent).where(qContent.id.eq(content.getId()).and(qContent.contentId.eq(content.getContentId()))).fetchFirst();
        if(content.getId() == null){

            queryFactory.insert(qContent)
                    .columns(qContent.unitId, qContent.contentId, qContent.content, qContent.contentOrder, qContent.quizList, qContent.group1, qContent.group2, qContent.group3, qContent.group4, qContent.longDate, qContent.state)
                    .values(content.getUnitId(), content.getContentId(), content.getContent(), content.getContentOrder(), content.getQuizList(), content.getGroup1(), content.getGroup2(), content.getGroup3(), content.getGroup4(), content.getLongDate(), content.getState())
                    .execute();

        }else{

            queryFactory.update(qContent)
                    .set(qContent.unitId, content.getUnitId())
                    .set(qContent.contentId, content.getContentId())
                    .set(qContent.content, content.getContent())
                    .set(qContent.contentOrder, content.getContentOrder())
                    .set(qContent.quizList, content.getQuizList())
                    .set(qContent.group1, content.getGroup1())
                    .set(qContent.group2, content.getGroup2())
                    .set(qContent.group3, content.getGroup3())
                    .set(qContent.group4, content.getGroup4())
                    .set(qContent.longDate, content.getLongDate())
                    .set(qContent.state, content.getState())
                    .where(qContent.id.eq(content.getId()).and(qContent.contentId.eq(content.getContentId())))
                    .execute();
        }

        return queryFactory.selectFrom(qContent).where(qContent.contentId.eq(content.getContentId())).fetchFirst();
    }

    public List<Content> delete(Content content){
//        public List<Content> delete(long id, long unitId){
        QContent qContent = QContent.content1;

        queryFactory.delete(qContent)
                .where(qContent.id.eq(content.getId()))
                .execute();

        List<Content> ContentList;

        if(content.getUnitId() == 0){
            ContentList = findContentsOrderByContentId();
        }else{
            ContentList = findContentsByUnitIdOrderByContentId(content.getUnitId());
        }

        return ContentList;
    }


}
