package se.nackademin.restcms.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "admin")
public class Admin {

    public Admin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String email;
    private String password;

    //fk
    @JoinColumn(name = "blog_id", table = "blog")
    private int blogid;

}
