package lake.pool.springdatajpa;

import lake.pool.springdatajpa.customrepository.commonrepository.CommonRepository;
import lake.pool.springdatajpa.customrepository.commonrepository.CommonRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CommonRepositoryImpl.class) // 커스텀한 공통 repository 설정 // 이거 설정시 JpaRepostory 못씀?
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
