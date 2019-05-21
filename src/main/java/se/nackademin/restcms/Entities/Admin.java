package se.nackademin.restcms.Entities;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "admin_id")
    private Integer id;
    private String email;
    private String password;

    //fk
    @JoinColumn(name = "blog_id", table = "blog")
    private int blogid;

}
