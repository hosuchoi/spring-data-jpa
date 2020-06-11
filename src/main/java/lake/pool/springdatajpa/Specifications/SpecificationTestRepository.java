package lake.pool.springdatajpa.Specifications;

import lake.pool.springdatajpa.common.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SpecificationTestRepository extends JpaRepository<Comment, Long>, JpaSpecificationExecutor<Comment> {
}
