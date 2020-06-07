package lake.pool.springdatajpa.samplerepository;

import lake.pool.springdatajpa.common.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest // slice test : Data access layer(Repository layer) 영역의 bean들만 만들어 줌 ( 빠르게 Repository test 가능 ) << Junit 테스트에서는 인메모리 설정되어 있으면 인메모리 사용함
@Slf4j
public class SampleAccountRepositoryTest {

    @Autowired
    SampleAccountRepository sampleAccountRepository;

    /*
    @NoneNull 테스트
     */
    @Test
    public void nonnullTest(){
        //sampleJpaRepository.countByUsernameContains() 에 @NonNull 설정 했음
        sampleAccountRepository.countByUsernameContains(null); // 표시 해줌 >> Passing 'null' argument to parameter annotated as @NotNull
    }

    /*
     @Rollback(false) 처리한 이유
     @DataJpaTest 안에 @Transactional포함되어 있으므로 특성상 test인걸 인지하고 Rollback 처리 함
     hibernate에서 Test인줄 알고 실제 저장 action에 대해서는 처리 안함( 그래서 아래 insert문 안날림 )
     */
    @Test
    @Rollback(false) // @Rollback(false) 처리해주면 insert문 처리됨
    public void save(){
        // GIVEN
        Account account = new Account();
        account.setPassword("1234");
        account.setUsername("lake");

        // Then
        assertThat(account.getId()).isNull();

        // When
        Account save = sampleAccountRepository.save(account);

        // Then
        assertThat(save.getId()).isNotNull();

        // When
        List<Account> accounts = sampleAccountRepository.findAll();

        // Then
        assertThat(accounts.size()).isEqualTo(1);
        assertThat(accounts).contains(account);

        // When
        Page<Account> pages = sampleAccountRepository.findAll(PageRequest.of(0, 10));

        // Then
        assertThat(pages.getTotalElements()).isEqualTo(1);
        assertThat(pages.getNumber()).isEqualTo(0);
        assertThat(pages.getSize()).isEqualTo(10);
        assertThat(pages.getNumberOfElements()).isEqualTo(1);

        // When
        // 첫번째 page의 10개 row를 조회해라!!
        pages = sampleAccountRepository.findByUsernameContains("lake", PageRequest.of(0, 10));
        // Then
        assertThat(pages.getTotalElements()).isEqualTo(1);
        assertThat(pages.getNumber()).isEqualTo(0);
        assertThat(pages.getSize()).isEqualTo(10);
        assertThat(pages.getNumberOfElements()).isEqualTo(1);

        // When
        List<Account> content = pages.getContent();

        // Then
        assertThat(content.get(0)).isEqualTo(account);

        // When
        long count = sampleAccountRepository.countByUsernameContains("lake");

        // Then
        assertThat(count).isEqualTo(1);
    }

    @Test
    public void jpqlTest(){
        createUSer();

//        List<Account> byUsernameJpqlQuery = sampleJpaRepository.findByUsernameJpqlQuery("lake%", Sort.by(Sort.Direction.DESC, "username"));  // property or as 만 가능
        List<Account> byUsernameJpqlQuery = sampleAccountRepository.findByUsernameJpqlQuery("lake%", JpaSort.unsafe(Sort.Direction.DESC, "LENGTH(username)")); //함수를 이용한 경우
        assertThat(byUsernameJpqlQuery.size()).isEqualTo(10);
    }

    public void createUSer() {
        int pageSize = 10;
        while(pageSize > 0){
            Account account = new Account();
            account.setUsername("lake"+pageSize);
            sampleAccountRepository.save(account);
            pageSize--;
        }
    }

    @Test
    public void customUpdate(){
        Account account = new Account();
        account.setUsername("lake");
        Account savedAccount = sampleAccountRepository.save(account);

        int lakelove = sampleAccountRepository.updateUsername("lakelove", savedAccount.getId());
        assertThat(lakelove).isEqualTo(1);

        Optional<Account> byId = sampleAccountRepository.findById(savedAccount.getId());
        Account account1 = byId.get();
        assertThat(account1.getUsername()).isEqualTo("lakelove"); // lakelove 이겟지?? 하겟지만 아니다!!
                                                                  // 이유는 ? select를 안하고 캐시에 있는 예전 객체를 씀. 
                                                                  // @Modifying(clearAutomatically = true) -> clearAutomatically = true 캐싱 초기화 ->잘됨.
                                                                  // 비추천.... jpa의 생태계를 더럽힌다!!

        account1.setUsername("UU");  // 어라? set만 했는데 업데이트 되네? 해당 객체는 persist상태이므로 해당값을 set하면 transaction이 끝날때 set한 값으로 반영해줌.
                                     // 이 방법 추천!!
        assertThat(sampleAccountRepository.findById(savedAccount.getId()).get().getUsername()).isEqualTo("UU");
    }
}