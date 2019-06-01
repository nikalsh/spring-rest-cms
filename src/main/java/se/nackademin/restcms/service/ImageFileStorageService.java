package se.nackademin.restcms.service;

import org.springframework.web.multipart.MultipartFile;
import se.nackademin.restcms.entities.BlogAdmin;
import se.nackademin.restcms.entities.ImageFile;
import se.nackademin.restcms.exception.MyFileNotFoundException;

public interface ImageFileStorageService {

ImageFile storeImageFile (MultipartFile file);
ImageFile storeImageFile (MultipartFile file, BlogAdmin blogAdmin);
ImageFile getFile (String fileId);
ImageFile getProfilePicture (BlogAdmin blogAdmin);

}
