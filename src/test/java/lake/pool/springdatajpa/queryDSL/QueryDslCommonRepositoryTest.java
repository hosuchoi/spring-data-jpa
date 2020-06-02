package lake.pool.springdatajpa.queryDSL;

import com.querydsl.core.types.Predicate;
import lake.pool.springdatajpa.common.entity.Account;
import lake.pool.springdatajpa.common.entity.QAccount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class QueryDslCommonRepositoryTest {

    @Autowired
    QueryDslRepository queryDslRepository;

    @Test
    @Rollback(false)
    public void queryDslTest(){
        Account createAccount = new Account();
        createAccount.setFirstName("lake");
        createAccount.setLastName("choi");
        queryDslRepository.save(createAccount);

        QAccount account = QAccount.account;
        Predicate predicate = account
                .firstName.containsIgnoreCase("ak")
                .and(account.lastName.startsWith("ch"));

        Optional<Account> one = queryDslRepository.findOne(predicate);
        assertThat(one).isNotEmpty();
    }
}