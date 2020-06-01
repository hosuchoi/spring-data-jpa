package lake.pool.springdatajpa.makequery;

import lake.pool.springdatajpa.common.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@DataJpaTest
@Slf4j
public class MakeQueryAsyncRepositoryTest {

    @Autowired
    MakeQueryAsyncRepository makeQueryAsyncRepository;

    @Test
    @Rollback(false)
    public void futureTest() throws Exception {
        // Given
        Account lake1 = this.createAccount("lake1", 100);
        Account lake2 = this.createAccount("lake2", 1);
        Account lake3 = this.createAccount("lake3", 50);

        // When
        PageRequest pageAge = PageRequest.of(0, 2, Sort.by(Sort.Direction.DESC, "age"));
        Future<List<Account>> futureLakes = makeQueryAsyncRepository.findByUsernameContainsIgnoreCase("lakE", pageAge);

        // Then  << 결코 Async 처리가 안됨... test 코드 작성이 넘 힘들어서 포기
        log.debug("======================================");
        log.debug("결과 나왓나 ? >> " +  futureLakes.isDone()); // 결과가 나왓는지 확인

        List<Account> waitAccounts = futureLakes.get();// get() 은 결과가 나올때 까지 기다림
        waitAccounts.forEach((s)  -> log.debug(s.toString()));

    }

    @Test
    @Rollback(false)
    public void listenableFutureTest() throws Exception{
        // Given
        Account lake1 = this.createAccount("lake1", 100);
        Account lake2 = this.createAccount("lake2", 1);
        Account lake3 = this.createAccount("lake3", 50);

        makeQueryAsyncRepository.flush(); // 바로 쿼리가 날라감
        List<Account> all = makeQueryAsyncRepository.findAll();
        log.debug("바로날라갔냐?? >> " + all.size());

        // When
        PageRequest pageAge = PageRequest.of(0, 2, Sort.by(Sort.Direction.DESC, "age"));
        ListenableFuture<List<Account>> futureLakes = makeQueryAsyncRepository.findByUsernameContainsIgnoreCaseOrderByAgeDesc("lakE", pageAge);

        // Then  << 결코 Async 처리가 안됨... test 코드 작성이 넘 힘들어서 포기
        log.debug("======================================");
        log.debug("결과 나왓나 ? >> " +  futureLakes.isDone()); // 결과가 나왓는지 확인

        futureLakes.addCallback(new ListenableFutureCallback<List<Account>>() {
            @Override
            public void onFailure(Throwable throwable) {
                //error
                throwable.printStackTrace();
            }

            @Override
            public void onSuccess(List<Account> accounts) {
                // 완료 되었을 경우
                log.debug("========================");
                accounts.forEach((s) -> log.debug(s.toString()));
            }
        });
    }

    private Account createAccount(String username, int age){
        Account account = new Account();
        account.setUsername(username);
        account.setAge(age);

        makeQueryAsyncRepository.save(account);

        return account;
    }
}