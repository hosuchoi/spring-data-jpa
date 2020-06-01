package lake.pool.springdatajpa.customrepository.postcustom;

import lake.pool.springdatajpa.common.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>, PostCustomRepository<Post> {
}
