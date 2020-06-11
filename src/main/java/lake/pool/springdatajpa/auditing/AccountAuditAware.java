package lake.pool.springdatajpa.auditing;

import lake.pool.springdatajpa.common.entity.Account;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountAuditAware implements AuditorAware<Account> {

    @Override
    public Optional<Account> getCurrentAuditor() {
        System.out.println("====== auditorAware execute =====");
        Account account = new Account();
        account.setUsername("lake");

        return Optional.of(account);
        
        /*
        // 인증에 담겨있는 사용자 정보를 넘겨주는게 정석임
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         if(authentication == null || !authentication.isAuthenticated()){
            return null;
         }
         
         return ((MyUserDetails) authentication.getPrincipal()).getUser();
         */
    }
}
