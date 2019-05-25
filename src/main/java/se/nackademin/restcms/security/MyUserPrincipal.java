package se.nackademin.restcms.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import se.nackademin.restcms.entities.BlogAdmin;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MyUserPrincipal implements UserDetails {
    private BlogAdmin blogAdmin;

    public MyUserPrincipal(BlogAdmin blogAdmin) {
        this.blogAdmin = blogAdmin;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.blogAdmin.getPassword();
    }

    @Override
    public String getUsername() {
        return this.blogAdmin.getName();
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
        return true;
    }

    public BlogAdmin getBlogAdmin() {
        return blogAdmin;
    }
}

