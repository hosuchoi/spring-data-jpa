package lake.pool.springdatajpa.samplerepository;

import lake.pool.springdatajpa.common.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SamplePostRepository extends JpaRepository<Post,Long>{

}
