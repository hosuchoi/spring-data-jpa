package lake.pool.springdatajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableJpaRepositories(repositoryBaseClass = CommonRepository.class) // 커스텀한 공통 repository 설정 // 이거 설정시 JpaRepostory 못씀?
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
