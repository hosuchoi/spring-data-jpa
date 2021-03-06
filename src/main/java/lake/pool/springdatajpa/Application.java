package lake.pool.springdatajpa;

import lake.pool.springdatajpa.customrepository.commonrepository.CommonRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CommonRepositoryImpl.class) // 커스텀한 공통 repository 설정
@EnableJpaAuditing(auditorAwareRef = "accountAuditAware") // Auditing 기능 설정
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
