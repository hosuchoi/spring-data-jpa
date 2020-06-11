package lake.pool.springdatajpa.common.entity.BaseEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseEntityRepository extends JpaRepository<UserEntity, Long> {
}
