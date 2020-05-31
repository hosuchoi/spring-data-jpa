package lake.pool.springdatajpa.customRepository;

import org.springframework.data.domain.Example;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.List;

/*
 사용 하고 싶은 메서드만 재정의해서 사용하면 됨
 */
@NoRepositoryBean
public interface CustomInterfaceRepository<T, Id extends Serializable> extends Repository<T, Id> {

    <S extends T> List<S> findAll(Example<S> example);

    List<T> findAll();
}

/*
아래와 같이 custume한 repository를 상속 받아서 사용하면됨.
public interface CustomRepository extends CustomInterfaceRepositoryRepository<Account, Long> {

}
 */