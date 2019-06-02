package se.nackademin.restcms.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@ToString(exclude = {"blogAdmin"})
@NoArgsConstructor
@Table(name = "blog")
@JsonIgnoreProperties(value = {"blogAdmin"})

public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "blog_id")
    private Long id;


    private String blogName;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<BlogPost> blogPosts;

    @OneToOne
    private BlogAdmin blogAdmin;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date updateDate;

    @Column(name = "is_active", columnDefinition = "boolean default true")
    private boolean active;


}
