package tw.intelegence.ncsist.sstp.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.LockModeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import jakarta.persistence.EntityManager;

@Configuration
@EnableJpaRepositories(basePackages = "tw.intelegence.ncsist.sstp")  // 替換為您的基本包名
public class QuerydslConfig {

    @Autowired
    EntityManager entityManager;

    private JPAQueryFactory jpaQueryFactory;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
//    public JPAQueryFactory jpaQueryFactory(EntityManager entityManager) {
        jpaQueryFactory = new JPAQueryFactory(entityManager);
        return jpaQueryFactory;
    }

    @PreDestroy
    public void cleanup(){
//        entityManager.
        System.out.println("cleanup");
//        if(entityManager.getTransaction().isActive()){
//            System.out.println("isActive");
//            entityManager.getTransaction().rollback();
//
//            System.out.println("rollback");
//        }

        if(entityManager.isOpen()){
            System.out.println("isOpen");
            entityManager.clear();
            entityManager.close();
        }
    }

}