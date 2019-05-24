package se.nackademin.restcms.service;

import org.springframework.web.multipart.MultipartFile;
import se.nackademin.restcms.entities.ImageFile;

public interface ImageFileStorageService {

ImageFile storeImageFile (MultipartFile file);
ImageFile getFile (String fileId);

}
