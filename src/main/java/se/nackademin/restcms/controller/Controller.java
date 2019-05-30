package se.nackademin.restcms.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import se.nackademin.restcms.crudrepositories.AuthorityRepository;
import se.nackademin.restcms.crudrepositories.BlogAdminRepository;
import se.nackademin.restcms.entities.Authority;
import se.nackademin.restcms.entities.AuthorityType;
import se.nackademin.restcms.entities.BlogAdmin;
import se.nackademin.restcms.entities.ImageFile;
import se.nackademin.restcms.payload.UserCreatedResponse;
import se.nackademin.restcms.service.ImageFileStorageService;

@RestController
public class Controller {

    @Autowired
    BlogAdminRepository blogAdminRepository;

    @Autowired
    private AuthorityRepository authorityRepository;
    
    @Autowired
private ImageFileStorageService imageFileStorageService;

@Autowired
PasswordEncoder passwordEncoder;

    @GetMapping("/test")
    public String result() {
        return "endpoint exposed";
    }

@CrossOrigin(origins = "http://localhost:8081")
@PostMapping("/registerUser")
public UserCreatedResponse registerUser(@RequestParam String email, @RequestParam String name, @RequestParam String password, @RequestParam("file") MultipartFile file) {
    
    
    BlogAdmin blogAdmin = new BlogAdmin (authorityRepository.getOne (0));
    ImageFile imageFile = imageFileStorageService.storeImageFile (file, blogAdmin);
    blogAdmin.setEmail (email);
    blogAdmin.setName (name);
    blogAdmin.setPassword (passwordEncoder.encode(password));
    
    blogAdminRepository.save (blogAdmin);
    
    return new UserCreatedResponse (blogAdmin.getId (), blogAdmin.getEmail (), blogAdmin.getName () );
}

//    11	johan@johan.johan		$2a$11$gYak/d176J8BjSww8yXd9OofBSf0ScBta0qW/zyIniyIVlSAYSIAG
    


}
