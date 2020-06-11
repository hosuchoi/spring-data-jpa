package lake.pool.springdatajpa.common.entity.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class UserEntity extends BaseEntity implements Serializable {

    private static final long serialVersingUID = -312324122332123L;

    private String username;

    private String firstName;

    private String lastName;

    private String password;

    private Integer age;
}
