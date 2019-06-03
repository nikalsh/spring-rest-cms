//package se.nackademin.restcms.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import se.nackademin.restcms.crudrepositories.BlogAdminRepository;
//import se.nackademin.restcms.entities.BlogAdmin;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    private final BlogAdminRepository blogAdminRepository;
//
//    @Autowired
//    public UserDetailsServiceImpl(BlogAdminRepository blogAdminRepository) {
//        this.blogAdminRepository = blogAdminRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String email) {
//        BlogAdmin user = blogAdminRepository.findByEmail(email);
//        if (user == null) {
//            throw new UsernameNotFoundException(email);
//        }
//        return new UserDetailsImpl(user);
//    }
//}
