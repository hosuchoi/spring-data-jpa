package lake.pool.springdatajpa.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;


//Pojection : 특정 칼럼만 조회 - interface기반 , class 기반

//EntityGragh
@EntityListeners(AuditingEntityListener.class) // Auditing 리스너 설정
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

    /*
     아래는 Auditing 기능
     */
    /*
    방법 1) Auditing 설정
     */
    @CreatedDate
//    @Column(updatable = false)
//    @Convert(converter = LocalDateTimePersistenceConverter.class)
    private Date created;

    @ManyToOne(cascade = CascadeType.ALL)
    @CreatedBy
//    @Column(updatable = false)
    private Account createdBy;

    @LastModifiedDate
//    @Convert(converter = LocalDateTimePersistenceConverter.class)
    private Date updated;

    @ManyToOne(cascade = CascadeType.ALL)
    @LastModifiedBy
    private Account updatedBy;

    /*
    방법2) 콜백 함수 이용
    https://docs.jboss.org/hibernate/orm/4.0/hem/en-US/html/listeners.html
     */
    @PrePersist
    public void prePersist(){
        System.out.println("prePersist");
//        this.created = new Data();
    }
}

