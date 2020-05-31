package lake.pool.springdatajpa.makeQuery;

import lake.pool.springdatajpa.common.entity.Account;
import lake.pool.springdatajpa.makeQeury.MakeQueryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MakeQueryRepositoryTest {

    @Autowired
    MakeQueryRepository makeQueryRepository;


    @Test
    public void queryTest(){
        // Given
        Account lake = this.createAccount("lake", 50);
        Account lake1 = this.createAccount("lake1", 100);
        Account lake2 = this.createAccount("lake2", 1);
        Account lake3 = this.createAccount("lake2", 30);

        // When
        List<Account> lakes1 = makeQueryRepository.findByUsername("lake");
        List<Account> lakes2 = makeQueryRepository.findByUsernameContains("lake");
        List<Account> lakes3 = makeQueryRepository.findByUsernameContaining("lake");
        List<Account> lakes4 = makeQueryRepository.findByUsernameIsContaining("lake");
        List<Account> lakes5 = makeQueryRepository.findByUsernameLike("%lake%"); // 별로네?

        // Then
        assertThat(lakes1.size()).isEqualTo(1);
        assertThat(lakes2.size()).isEqualTo(4);
        assertThat(lakes3.size()).isEqualTo(4);
        assertThat(lakes4.size()).isEqualTo(4);
        assertThat(lakes5.size()).isEqualTo(4);

        // When
        List<Account> lakes = makeQueryRepository.findByUsernameContainsIgnoreCaseAndAgeGreaterThan("LakE", 10);

        // Then
        assertThat(lakes.size()).isEqualTo(3);

        // When
        List<Account> lakeDesc = makeQueryRepository.findByUsernameContainsIgnoreCaseOrderByAgeDesc("LakE");

        // Then
        assertThat(lakeDesc.get(0)).isEqualTo(lake1);
        assertThat(lakeDesc).first().hasFieldOrPropertyWithValue("age", 100);

        // When
        // PageRequest가 Pageable 타입임
        PageRequest agePage = PageRequest.of(0, 2, Sort.by(Sort.Direction.ASC, "age"));
        Page<Account> lakeAscPage = makeQueryRepository.findByUsernameContainsIgnoreCaseAndAgeGreaterThan("LakE", 20, agePage);

        //Then
        assertThat(lakeAscPage.getTotalPages()).isEqualTo(2);
        assertThat(lakeAscPage.getTotalElements()).isEqualTo(3);
        assertThat(lakeAscPage.getNumberOfElements()).isEqualTo(2);
        assertThat(lakeAscPage).first().hasFieldOrPropertyWithValue("age", 30);
        
        // When
        try(Stream<Account> lakeStream = makeQueryRepository.findByUsernameContainsIgnoreCase("lakE", agePage)){
            Account account = lakeStream.findFirst().get();
            assertThat(account).isEqualTo(lake2);
        }

    }

    private Account createAccount(String username, int age){
        Account account = new Account();
        account.setUsername(username);
        account.setAge(age);

        makeQueryRepository.save(account);

        return account;
    }
}