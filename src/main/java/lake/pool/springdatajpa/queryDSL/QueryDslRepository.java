package lake.pool.springdatajpa.queryDSL;

import lake.pool.springdatajpa.common.entity.Account;
import lake.pool.springdatajpa.customrepository.commonrepository.CommonRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface QueryDslRepository extends JpaRepository<Account, Long>, QuerydslPredicateExecutor<Account> {
}
