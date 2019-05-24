package se.nackademin.restcms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import se.nackademin.restcms.CrudRepositories.BlogAdminRepository;
import se.nackademin.restcms.CrudRepositories.ImageFileRepository;
import se.nackademin.restcms.Entities.ImageFile;
import se.nackademin.restcms.exception.FileStorageException;
import se.nackademin.restcms.exception.MyFileNotFoundException;

import java.io.IOException;

@Service
public class ImageFileStorageServiceImpl implements ImageFileStorageService {

@Autowired
private ImageFileRepository imageFileRepository;

@Autowired
private BlogAdminRepository blogAdminRepository;

@Override
public ImageFile storeImageFile (MultipartFile file) {
	String fileName = StringUtils.cleanPath (file.getOriginalFilename ());
	try {
		if (fileName.contains ("..")) {
			throw new FileStorageException ("Sorry! Filename contains invalid path sequence " + fileName);
		}
		
		ImageFile imageFile = new ImageFile (fileName, file.getContentType (), file.getBytes ());
		
		imageFile.setBlogAdmin (blogAdminRepository.getOne ((long) 1));
		
		return imageFileRepository.save (imageFile);
	} catch (IOException ex) {
		throw new FileStorageException ("Could not store file " + fileName + ". Please try again!", ex);
	}
}

@Override
public ImageFile getFile (String fileId) {
	return imageFileRepository.findById (fileId)
			.orElseThrow (() -> new MyFileNotFoundException ("File not found with id " + fileId));
}
}
