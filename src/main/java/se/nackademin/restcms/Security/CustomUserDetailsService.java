package se.nackademin.restcms.Security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.nackademin.restcms.entities.CustomUserDetail;
import se.nackademin.restcms.crudrepositories.BlogAdminRepository;
import se.nackademin.restcms.entities.BlogAdmin;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final BlogAdminRepository blogAdminRepository;

    @Autowired
    public CustomUserDetailsService( BlogAdminRepository blogAdminRepository) {
        this.blogAdminRepository=blogAdminRepository;
    }
    @Override
    @Transactional(readOnly = true)

    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        final BlogAdmin blogAdmin = blogAdminRepository.findByEmail(name);
        if (blogAdmin==null) {
            throw new UsernameNotFoundException("Could not find the user " + name);
        }
        List<SimpleGrantedAuthority> collect = blogAdmin.getRole()
                .stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());
        System.out.println(collect.size());
        return new CustomUserDetail(blogAdmin);
    }

}