package lake.pool.springdatajpa.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    private String title; //default varchar(255)

    @Lob // 255자가 넘을 경우
    private String comment;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
}
