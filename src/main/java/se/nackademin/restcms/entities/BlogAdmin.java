package se.nackademin.restcms.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.*;
import org.h2.result.SearchRow;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static lombok.AccessLevel.PROTECTED;

@Data
@NoArgsConstructor
@Entity
@ToString(exclude = {"blog"})
@Table(name = "blogadmin")
@JsonIgnoreProperties(value = {"blog"})
public class BlogAdmin implements Serializable {

    @Getter(PROTECTED)
    @Setter(PROTECTED)
    @Ignore
    protected String _class;

    public BlogAdmin(String email, String password, Authority authority) {
        this.role.add(authority);
        this.email = email;
        this.password = password;
        isEnabled=true;
    }

    public BlogAdmin(String email, String password) {
        this.email = email;
        this.password = password;
        isEnabled=true;
    }



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "blogadmin_id")
    private Long id;

    private String email;
    private String name;
    private String password;
    private Boolean isEnabled;

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
