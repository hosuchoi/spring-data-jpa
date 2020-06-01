package lake.pool.springdatajpa.customrepository.postcustom;

import lake.pool.springdatajpa.common.entity.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
@Slf4j
public class PostCustomRepositoryImpl<T> implements PostCustomRepository<T>{  // ~~Impl 필수

    @Autowired
    EntityManager entityManager;
    
    @Autowired
    JdbcTemplate jdbcTemplate; // jdbc template도 사용 가능

    @Override
    public List<Post> findMyPost() {
        log.debug("custom findMyPost");
        return entityManager.createQuery("SELECT p FROM Post AS p", Post.class)   //JPQL
                .getResultList();
    }

    @Override
    public void delete(T entity) {
        log.debug("custom delete");
        entityManager.remove(entity);
    }
}
