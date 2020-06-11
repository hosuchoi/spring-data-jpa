package lake.pool.springdatajpa.common.entity.BaseEntity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseEntityRepositoryTest {

    @Autowired
    BaseEntityRepository repository;

    @Test
    public void baseEntitys(){
        UserEntity userEntity = UserEntity.builder().firstName("lake")
                .lastName("choi")
                .age(33)
                .build();

        UserEntity savedUser = repository.save(userEntity);
        repository.flush();
        repository.findAll();

        savedUser.setAge(34);
        repository.save(savedUser);
        repository.flush();
        repository.findAll();
    }

}