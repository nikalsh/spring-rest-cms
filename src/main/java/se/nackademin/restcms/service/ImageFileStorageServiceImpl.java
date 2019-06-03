package se.nackademin.restcms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import se.nackademin.restcms.crudrepositories.BlogAdminRepository;
import se.nackademin.restcms.crudrepositories.ImageFileRepository;
import se.nackademin.restcms.entities.BlogAdmin;
import se.nackademin.restcms.entities.ImageFile;
import se.nackademin.restcms.exception.FileStorageException;
import se.nackademin.restcms.exception.MyFileNotFoundException;

import java.io.IOException;

@Service
public class ImageFileStorageServiceImpl implements ImageFileStorageService {

private final ImageFileRepository imageFileRepository;

private final BlogAdminRepository blogAdminRepository;

	@Autowired
	public ImageFileStorageServiceImpl(ImageFileRepository imageFileRepository, BlogAdminRepository blogAdminRepository) {
		this.imageFileRepository = imageFileRepository;
		this.blogAdminRepository = blogAdminRepository;
	}

	@Override
public ImageFile storeImageFile (MultipartFile file) {
	String fileName = StringUtils.cleanPath (file.getOriginalFilename ());
	try {
		if (fileName.contains ("..")) {
			throw new FileStorageException ("Sorry! Filename contains invalid path sequence " + fileName);
		}
		
		ImageFile imageFile = new ImageFile (fileName, file.getContentType (), file.getBytes ());
		
		imageFile.setBlogAdmin (blogAdminRepository.getOne (3L));
		
		return imageFileRepository.save (imageFile);
	} catch (IOException ex) {
		throw new FileStorageException ("Could not store file " + fileName + ". Please try again!", ex);
	}
}
@SuppressWarnings ("Duplicates")
@Override
public ImageFile storeImageFile (MultipartFile file, BlogAdmin blogAdmin) {
	String fileName = StringUtils.cleanPath (file.getOriginalFilename ());
	try {
		if (fileName.contains ("..")) {
			throw new FileStorageException ("Sorry! Filename contains invalid path sequence " + fileName);
		}
		
		ImageFile imageFile = new ImageFile (fileName, file.getContentType (), file.getBytes ());
		
		imageFile.setBlogAdmin (blogAdmin);
		
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

@Override
public ImageFile getProfilePicture (BlogAdmin blogAdmin) {
	return null;
}
}
