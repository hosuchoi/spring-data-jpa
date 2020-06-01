package lake.pool.springdatajpa.makequery;

import lake.pool.springdatajpa.common.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.concurrent.Future;

public interface MakeQueryAsyncRepository extends JpaRepository<Account, Long> {
    /*
    ===== 비추천 쓰지 말자 개복잡 + 비동기로 하더라도 db 부하는 동일 // 단지 메인 스레드가 놀지 않고 다른일을 할 수 있다! 요정도? =====
    ===== 최고의 성능 향상은 쿼리 갯수 줄이고, 필요한 데이터만 조회하는게 최고임!! ======

    @Async만 한다고 비동기적으로 처리 안됨, 해당 기능 설정시 백그라운드의 thread pool에 메세드 실행을 위임(별도의 스레드에 위임)
    Asyncronized 하게하려면 아래 추가 !!
    1) Future<List<Account>> << 리턴타입을 Future<>로 감싼다 ( java 1.5 버젼 )  << 별로 Async 하지 않음.. 버리자 ㅋㅋㅋ
    2) CompletableFuture<List<Account>> << Java 8
    3) ListenableFuture<List<Account>> << Spring에서 만듬 <<<<< 추천 가장 깔끔
     */
    @Async
    Future<List<Account>> findByUsernameContainsIgnoreCase(String username, Pageable pageable);

    @Async
    ListenableFuture<List<Account>> findByUsernameContainsIgnoreCaseOrderByAgeDesc(String username, Pageable pageable);

}
