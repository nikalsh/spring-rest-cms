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
    @Column(name = "blogadmin_id")
    private Long id;

    private String email;
    private String name;
    private String password;

@OneToOne(mappedBy = "blogAdmin",
        cascade = CascadeType.ALL, orphanRemoval = true)
private Blog blog;

    @Lob
    @Column(name = "photo", columnDefinition = "BLOB")
    private byte[] photo;

public void addBlog(Blog blog) {
    this.blog = blog;
    blog.setBlogAdmin (this);
}

public void removeBlog(Blog blog) {
    if (blog != null) {
        blog.setBlogAdmin (null);
    }
    this.blog = null;
}

}
