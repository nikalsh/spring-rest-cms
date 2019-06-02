package se.nackademin.restcms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.nackademin.restcms.crudrepositories.AuthorityRepository;
import se.nackademin.restcms.crudrepositories.BlogAdminRepository;
import se.nackademin.restcms.entities.Authority;
import se.nackademin.restcms.entities.AuthorityType;
import se.nackademin.restcms.entities.Blog;
import se.nackademin.restcms.entities.BlogAdmin;

import javax.annotation.PostConstruct;

@Service
public class InitialDBData {

	private final BlogAdminRepository blogAdminRepository;

	private final AuthorityRepository authorityRepository;

	private final PasswordEncoder passwordEncoder;

@Autowired
public InitialDBData(BlogAdminRepository blogAdminRepository, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder) {
	this.blogAdminRepository = blogAdminRepository;
	this.authorityRepository = authorityRepository;
	this.passwordEncoder = passwordEncoder;
}

@PostConstruct
public void init () {
	Authority admin = new Authority(AuthorityType.ROLE_ADMIN);
	Authority user = new Authority(AuthorityType.ROLE_USER);
	authorityRepository.save(admin);
	authorityRepository.save(user);

//	BlogAdmin blogAdmin = new BlogAdmin("kuk@huvud.se", passwordEncoder.encode("kukhuve"), admin);
//	Blog blog = new Blog ();
//	blogAdmin.addBlog (blog);
//	blogAdminRepository.save (blogAdmin);

	BlogAdmin blogAdmin = new BlogAdmin("root@root.root", passwordEncoder.encode("root"), admin);
	Blog blog = new Blog();
	blogAdmin.addBlog(blog);
	blogAdminRepository.save(blogAdmin);
//
//	blogAdmin = new BlogAdmin("niklas@niklas.niklas", passwordEncoder.encode("niklas"), admin);
//	blog = new Blog ();
//	blogAdmin.addBlog (blog);
//	blogAdminRepository.save (blogAdmin);
//
//	blogAdmin = new BlogAdmin("josef@josef.josef", passwordEncoder.encode("josef"), admin);
//	blog = new Blog ();
//	blogAdmin.addBlog (blog);
//	blogAdminRepository.save (blogAdmin);
//
//	blogAdmin = new BlogAdmin("johan@johan.johan", passwordEncoder.encode("johan"), admin);
//	blog = new Blog ();
//	blogAdmin.addBlog (blog);
//	blogAdminRepository.save (blogAdmin);

}
}