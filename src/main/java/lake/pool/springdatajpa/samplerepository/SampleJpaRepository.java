package lake.pool.springdatajpa.samplerepository;

import lake.pool.springdatajpa.common.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface SampleJpaRepository extends JpaRepository<Account, Long> {

    Page<Account> findByUsernameContains(String username, Pageable pageable);

    long countByUsernameContains(@NonNull String username);

//    @Query(value = "SELECT c from Account AS c")
//    @Query(value = "SELECT username from Account where username = ${keyword}", nativeQuery = true)
    List<Account> findByUsername(String keyword);

    Page<Account> findByAgeGreaterThanAndUsernameOrderByUsername(int ageCount, String username, Pageable pageable);
    List<Account> findByAgeGreaterThanAndUsername(int ageCount, String username, Sort sort);
}
