package se.nackademin.restcms.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Entity
@ToString(exclude = {"blog"})
@Table(name = "user")
@JsonIgnoreProperties(value = {"blog"})
public class User implements UserDetails {
    public User(String username, String email, String password, Authority authority) {
        this.roles.add(authority);
        this.email = email;
        this.password = password;
        this.username=username;
        enabled=true;
    }
    public User(String email, String password, Authority authority) {
        this.roles.add(authority);
        this.email = email;
        this.password = password;
        enabled=true;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        enabled=true;
    }



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;
    @Column(name = "email", nullable = false)
    private String email;

    @OneToOne(mappedBy = "user", /* referes to the VARIABLE NAME */
            cascade = CascadeType.ALL, orphanRemoval = true)
    private Blog blog;

    @Lob
    @Column(name = "photo", columnDefinition = "BLOB")
    private byte[] photo;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> collect = roles
                .stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());
        return collect;
    }

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id")})
    private Set<Authority> roles = new HashSet<>();

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
