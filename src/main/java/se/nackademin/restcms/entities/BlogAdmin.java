package se.nackademin.restcms.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "blogadmin")
public class BlogAdmin {

    public BlogAdmin(String email, String password, Authority authority) {
        this.role.add(authority);
        this.email = email;
        this.password = password;
    }

    public BlogAdmin(String email, String password) {
        this.email = email;
        this.password = password;
    }



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "blogadmin_id")
    private Long id;

    private String email;
    private String name;
    private String password;

    @OneToOne(mappedBy = "blogAdmin", /* referes to the VARIABLE NAME */
            cascade = CascadeType.ALL, orphanRemoval = true)
    private Blog blog;

    @Lob
    @Column(name = "photo", columnDefinition = "BLOB")
    private byte[] photo;



    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",
            joinColumns = {@JoinColumn(name = "blogadmin_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id")})
    private Set<Authority> role = new HashSet<>();

    public void addBlog(Blog blog) {
        this.blog = blog;
        blog.setBlogAdmin(this);
    }

    public void removeBlog(Blog blog) {
        if (blog != null) {
            blog.setBlogAdmin(null);
        }
        this.blog = null;
    }

    public void addRole(Authority authority) {
        this.role.add(authority);
    }
}
