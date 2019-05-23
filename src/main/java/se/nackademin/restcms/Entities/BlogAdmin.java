package se.nackademin.restcms.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "blogAdmin")
public class BlogAdmin {

    public BlogAdmin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public BlogAdmin(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;
    private String name;
    private String password;

    //fk
    @JoinColumn(name = "blog_id", table = "blog")
    private int blogid;

    @Lob
    @Column(name = "photo", columnDefinition = "BLOB")
    private byte[] photo;

}
