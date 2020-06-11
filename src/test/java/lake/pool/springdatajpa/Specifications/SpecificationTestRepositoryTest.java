package lake.pool.springdatajpa.Specifications;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import static lake.pool.springdatajpa.Specifications.CommentSpecs.isBest;
import static lake.pool.springdatajpa.Specifications.CommentSpecs.isGood;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SpecificationTestRepositoryTest {

    @Autowired
    SpecificationTestRepository repository;

    @Test
    public void specs(){
//        repository.findAll(CommentSpecs.isBest().or(CommentSpecs.isGood()));
//        repository.findAll(CommentSpecs.isBest().and(CommentSpecs.isGood()));
        //위 소스를 static import를 써서 코드를 아래 처럼 줄일수 있다.
        repository.findAll(isBest().or(isGood()));
        repository.findAll(isBest().and(isGood()));
        //paging 처리도 가능
        repository.findAll(isBest().or(isGood()), PageRequest.of(0,10));
        repository.findAll(isBest().and(isGood()), PageRequest.of(0,10));
    }
}