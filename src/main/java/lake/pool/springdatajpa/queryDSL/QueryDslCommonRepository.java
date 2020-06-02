package lake.pool.springdatajpa.queryDSL;

import lake.pool.springdatajpa.common.entity.Account;
import lake.pool.springdatajpa.customrepository.commonrepository.CommonRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface QueryDslCommonRepository extends CommonRepository<Account, Long>, QuerydslPredicateExecutor<Account> {
}
