package se.nackademin.restcms.Entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String email;
    private String password;

    //fk
    @JoinColumn(name = "blog_id", table = "blog")
    private int blogid;

}
