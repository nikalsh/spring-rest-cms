package se.nackademin.restcms.service;

import org.springframework.stereotype.Service;
import se.nackademin.restcms.crudrepositories.BlogAdminRepository;
import se.nackademin.restcms.entities.Blog;
import se.nackademin.restcms.entities.BlogAdmin;

import javax.annotation.PostConstruct;

@Service
public class InitialDBData {

	private final BlogAdminRepository blogAdminRepository;

public InitialDBData (BlogAdminRepository blogAdminRepository) {
	this.blogAdminRepository = blogAdminRepository;
}

@PostConstruct
public void init () {
	
	BlogAdmin blogAdmin = new BlogAdmin ("kuk@huvud.se", "kukhuve");
	Blog blog = new Blog ();
	blogAdmin.addBlog (blog);
	blogAdminRepository.save (blogAdmin);
	
	blogAdmin = new BlogAdmin ("root@root.root", "root");
	blog = new Blog ();
	blogAdmin.addBlog (blog);
	blogAdminRepository.save (blogAdmin);
	
	blogAdmin = new BlogAdmin ("niklas@niklas.niklas", "niklas");
	blog = new Blog ();
	blogAdmin.addBlog (blog);
	blogAdminRepository.save (blogAdmin);
	
	blogAdmin = new BlogAdmin ("josef@josef.josef", "josef");
	blog = new Blog ();
	blogAdmin.addBlog (blog);
	blogAdminRepository.save (blogAdmin);
	
	blogAdmin = new BlogAdmin ("johan@johan.johan", "johan");
	blog = new Blog ();
	blogAdmin.addBlog (blog);
	blogAdminRepository.save (blogAdmin);
	
}
}