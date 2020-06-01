package lake.pool.springdatajpa.makequery;

import lake.pool.springdatajpa.common.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Stream;

public interface MakeQueryRepository extends JpaRepository<Account, Long> {

    /*
    order by  참고
   all = postRepositry.findByTitle("Spring", Sort.by("title"));
   all = postRepositry.findByTitle("Spring", JpaSort.unsafe("LENGTH(title)"));
     */

    List<Account> findByUsername(String username); // =
    List<Account> findByUsernameContains(String username); // like %username%
    List<Account> findByUsernameContaining(String username); // like %username%
    List<Account> findByUsernameIsContaining(String username); // like %username%
    List<Account> findByUsernameLike(String username); // like username  << %username% 이렇게 % 붙혀서 파라미터 줘야함

    List<Account> findByUsernameContainsIgnoreCaseAndAgeGreaterThan(String username, Integer age);
//    where
//     (
//    upper(account0_.username) like upper(?)
//      )
//    and account0_.age>?

    List<Account> findByUsernameContainsIgnoreCaseOrderByAgeDesc(String username);
//    where
//    upper(account0_.username) like upper(?)
//    order by
//    account0_.age desc

    Page<Account> findByUsernameContainsIgnoreCaseAndAgeGreaterThan(String username, int age, Pageable pageable);
//    where
//            (
//                    upper(account0_.username) like upper(?)
//            )
//    and account0_.age>?
//     ************ order by 어디감??? 왜 안찍힘??***********

    Stream<Account> findByUsernameContainsIgnoreCase(String username, Pageable pageable);
//    where
//    upper(account0_.username) like upper(?)
//    order by
//    account0_.age asc limit ?
}
