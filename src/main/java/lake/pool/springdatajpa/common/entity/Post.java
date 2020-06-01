package lake.pool.springdatajpa.common.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
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
