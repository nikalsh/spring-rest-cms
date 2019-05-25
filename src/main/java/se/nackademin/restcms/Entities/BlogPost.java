package se.nackademin.restcms.Entities;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

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
    private LocalDate created;

    @Column
    private LocalDate lastUpdated;

    @Column
    @Lob
    private String postData;

    public BlogPost() {
    }

    public BlogPost(String postData) {
        this.postData = postData;
    }
}
