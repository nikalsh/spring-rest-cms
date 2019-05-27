package se.nackademin.restcms.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "authority")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private AuthorityType type;

    public Authority(AuthorityType type) {
        this.type = type;
    }

    public String getName(){
        return type.toString();
    }

}
