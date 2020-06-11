package lake.pool.springdatajpa.common.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

/*
상속광계 매핑이 아니다.
@MappedSuperclass가 선언되어 있는 클래스는 엔티티가 아니다. 당연히 테이블과 매핑도 안된다.
단순히 부모 클래스를 상속 받는 자식 클래스에 매핑 정보만 제공한다.
 */
@Getter @Setter
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    private static final long serialVersingUID = 1146360943284237841L;

    @Id @GeneratedValue
    @Column(updatable = false, nullable = false, columnDefinition = "INT(11)")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date created;

    @Column(updatable = false, nullable = false)
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = true, columnDefinition = "TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP")
    private Date updated;

    @Column(updatable = true)
    private String updatedBy;

    @Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0", length = 1)
    private boolean del;

    @PrePersist
    protected void onCreate(){
        created = Timestamp.valueOf(LocalDateTime.now());
        // 인증 정보 사용자 ID
        createdBy = "lake";
    }

    @PreUpdate
    protected void onUpdate(){
        updated = Timestamp.valueOf(LocalDateTime.now());
        // 인증 정보 사용자 ID
        updatedBy = "lake";
    }

}
