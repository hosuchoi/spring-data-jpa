package lake.pool.springdatajpa.samplerepository;

import lake.pool.springdatajpa.common.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest // slice test : Data access layer(Repository layer) 영역의 bean들만 만들어 줌 ( 빠르게 Repository test 가능 ) << Junit 테스트에서는 인메모리 설정되어 있으면 인메모리 사용함
@Slf4j
public class SampleJpaRepositoryTest {

    @Autowired
    SampleJpaRepository sampleJpaRepository;

    /*
    @NoneNull 테스트
     */
    @Test
    public void nonnullTest(){
        //sampleJpaRepository.countByUsernameContains() 에 @NonNull 설정 했음
        sampleJpaRepository.countByUsernameContains(null); // 표시 해줌 >> Passing 'null' argument to parameter annotated as @NotNull
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
        Account save = sampleJpaRepository.save(account);

        // Then
        assertThat(save.getId()).isNotNull();

        // When
        List<Account> accounts = sampleJpaRepository.findAll();

        // Then
        assertThat(accounts.size()).isEqualTo(1);
        assertThat(accounts).contains(account);

        // When
        Page<Account> pages = sampleJpaRepository.findAll(PageRequest.of(0, 10));

        // Then
        assertThat(pages.getTotalElements()).isEqualTo(1);
        assertThat(pages.getNumber()).isEqualTo(0);
        assertThat(pages.getSize()).isEqualTo(10);
        assertThat(pages.getNumberOfElements()).isEqualTo(1);

        // When
        // 첫번째 page의 10개 row를 조회해라!!
        pages = sampleJpaRepository.findByUsernameContains("lake", PageRequest.of(0, 10));
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
        long count = sampleJpaRepository.countByUsernameContains("lake");

        // Then
        assertThat(count).isEqualTo(1);
    }
}