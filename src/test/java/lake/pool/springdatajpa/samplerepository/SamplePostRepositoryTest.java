package lake.pool.springdatajpa.samplerepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SamplePostRepositoryTest {

    @Autowired
    SamplePostRepository samplePostRepository;

    @Test
    public void getComment(){
        samplePostRepository.findById(1l);
    }

}