package se.nackademin.restcms.service;

import org.springframework.web.multipart.MultipartFile;
import se.nackademin.restcms.Entities.ImageFile;

public interface ImageFileStorageService {

ImageFile storeImageFile (MultipartFile file);
ImageFile getFile (String fileId);

}
