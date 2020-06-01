package lake.pool.springdatajpa.customrepository.postcustom;

import lake.pool.springdatajpa.common.entity.Post;

import java.util.List;

//완전 깔끔한 POJO ,
public interface PostCustomRepository<T> {

    List<Post> findMyPost();

    // 기본 기능 재정의해서 사용 ( delete가 인터페이스가 중복 됨 - 하지만 springboot jpa는 custom한 구현체를 우선시함)
    void delete(T entity);
}
