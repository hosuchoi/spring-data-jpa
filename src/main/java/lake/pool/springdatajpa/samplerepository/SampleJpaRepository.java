package lake.pool.springdatajpa.samplerepository;

import lake.pool.springdatajpa.common.entity.Account;
import lake.pool.springdatajpa.common.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.util.List;

public interface SampleJpaRepository extends JpaRepository<Account, Long> {

    Page<Account> findByUsernameContains(String username, Pageable pageable);

    long countByUsernameContains(@NonNull String username);

//    @Query(value = "SELECT c from Account AS c") //JPQL 쿼리
//    @Query(value = "SELECT username from Account where username = ${keyword}", nativeQuery = true) //Native 쿼리
    List<Account> findByUsername(String keyword);

    Page<Account> findByAgeGreaterThanAndUsernameOrderByUsername(int ageCount, String username, Pageable pageable);
    List<Account> findByAgeGreaterThanAndUsername(int ageCount, String username, Sort sort);

    //Sort
//    @Query("SELECT p FROM Account AS p WHERE p.username like ?1")
//    List<Account> findByUsernameJpqlQuery(String username, Sort sort);

    //Named Parameter
//    @Query("SELECT p FROM Account AS p WHERE p.username like :username")
//    List<Account> findByUsernameJpqlQuery(@Param("username") String value, Sort sort);

    //SePL : 여기서는 entity명을 받아와서 처리가능 -> entity 명을 바꿧을 경우 일괄적용됨
    @Query("SELECT p FROM #{#entityName} AS p WHERE p.username like :username")
    List<Account> findByUsernameJpqlQuery(@Param("username") String value, Sort sort);
}
