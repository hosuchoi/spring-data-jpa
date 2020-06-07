package lake.pool.springdatajpa.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//Pojection : 특정 칼럼만 조회 - interface기반 , class 기반

//EntityGragh
@NamedEntityGraph(name = "Comment.post", attributeNodes = @NamedAttributeNode("post")) // FetchType.EAGER 로 쓰겟다~ (함께조회하겠다 >> repository에 메서드별로 설정 가능)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {

    @Id @GeneratedValue
    private Long id;

    private String comment;

    @ManyToOne(fetch = FetchType.LAZY) // ManyToOne의 경우 기본값 FetchType.EAGER ( 함께 조회함 ) ( tip: 끝이 One으로 끝나면 기본값 EAGER )
    private Post post;

    private int up;

    private int down;

    private boolean best;

}
