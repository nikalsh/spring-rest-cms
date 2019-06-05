package se.nackademin.restcms.service;

import org.springframework.web.multipart.MultipartFile;
import se.nackademin.restcms.entities.User;
import se.nackademin.restcms.entities.ImageFile;

public interface ImageFileStorageService {

ImageFile storeImageFile (MultipartFile file);
//ImageFile storeImageFile (MultipartFile file, User user);
ImageFile getFile (String fileId);
ImageFile getProfilePicture (User user);

}
