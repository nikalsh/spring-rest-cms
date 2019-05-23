package se.nackademin.restcms.Entities;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.sql.Clob;

@Data
@Entity
@Table(name = "post")
public class Post {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "post_id")
private long id;

@ManyToOne
@JoinColumn(name="blog_id", nullable=false)
private Blog blog;

@Column
private LocalDate created;

@Column
private LocalDate lastUpdated;

@Column
private Clob postData;

}
