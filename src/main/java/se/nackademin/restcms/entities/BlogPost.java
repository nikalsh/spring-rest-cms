package se.nackademin.restcms.entities;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "blogpost")
public class BlogPost {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @ManyToOne
    @JoinColumn(name = "blog_id", nullable = false)
    private Blog blog;

    @Column
    private LocalDateTime created;

    @Column
    private LocalDateTime lastUpdated;

    @Column
    @Lob
    private String postData;

    public BlogPost() {
    }

    public BlogPost(String postData) {
        this.postData = postData;
    }

    public BlogPost(Blog blog) {
        this.blog = blog;
    }

    public BlogPost(Blog blog, String postData) {
        this.blog = blog;
        this.postData = postData;
    }

    public BlogPost(Blog blog, LocalDateTime created, String postData) {
        this.blog = blog;
        this.created = created;
        this.postData = postData;
    }
}
