package se.nackademin.restcms.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@ToString(exclude = {"user", "blogPosts", "headerImage"})
@NoArgsConstructor
@Table(name = "blog")
@JsonIgnoreProperties(value = {"user", "blogPosts"})
@EqualsAndHashCode(exclude = {"blogPosts", "user"})
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "blog_id")
    private Long id;

    @Column(name = "blogName", nullable = false, unique = true)
    private String blogName;

    @Lob
    @Column(name = "headerImage", columnDefinition = "BLOB")
    private byte[] headerImage;


    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
    private Set<BlogPost> blogPosts;

    @OneToOne
    private User user;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date dateCreated;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date dateUpdated;

    @Column(name = "active", columnDefinition = "boolean default true")
    private boolean active;


}
