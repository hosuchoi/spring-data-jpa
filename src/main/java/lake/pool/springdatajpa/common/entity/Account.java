package lake.pool.springdatajpa.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Account {

    @Id @GeneratedValue
    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private String password;

    private Integer age;

    public List<Account> getAcountList(){
        ArrayList<Account> arrAccount = new ArrayList<>();
        arrAccount.add(this);
        return arrAccount;
    }

    //Enumeration 매핑방법
    @Enumerated(value = EnumType.STRING) // EnumType.ORDINAL 비추 0,1,2 값으로 들어감
    private CommentStatus commentStatus;

    //Embeded
    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name = "street", column = @Column(name ="home_street"))
//    })
    private Address address;
}
