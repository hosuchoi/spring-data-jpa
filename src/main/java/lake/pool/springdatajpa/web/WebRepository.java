package lake.pool.springdatajpa.web;

import lake.pool.springdatajpa.common.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebRepository extends JpaRepository<Post, Long> {
}
