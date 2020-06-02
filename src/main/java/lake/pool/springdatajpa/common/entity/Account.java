package lake.pool.springdatajpa.common.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
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
}
