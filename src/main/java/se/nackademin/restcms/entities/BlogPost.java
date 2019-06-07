package se.nackademin.restcms.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "blogpost")
@NoArgsConstructor
@ToString(exclude = {"blog"})
@JsonIgnoreProperties({"blog"})
@EqualsAndHashCode(exclude={"blog"})
public class BlogPost implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;


    @ManyToOne
    @JoinColumn(name = "blog_id")
    private Blog blog;

//    @Column
//    private Boolean published;


    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date dateUpdated;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date dateCreated;

    @Column
    @Lob
    private String postData;


    public BlogPost(Blog blog, String postData) {
        this.blog = blog;
        this.postData = postData;
    }
}
