package se.nackademin.restcms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import se.nackademin.restcms.crudrepositories.UserRepository;
import se.nackademin.restcms.crudrepositories.ImageFileRepository;
import se.nackademin.restcms.entities.User;
import se.nackademin.restcms.entities.ImageFile;
import se.nackademin.restcms.exception.FileStorageException;
import se.nackademin.restcms.exception.MyFileNotFoundException;

import java.io.IOException;

@Service
public class ImageFileStorageServiceImpl implements ImageFileStorageService {

private final ImageFileRepository imageFileRepository;

private final UserRepository userRepository;

	@Autowired
	public ImageFileStorageServiceImpl(ImageFileRepository imageFileRepository, UserRepository userRepository) {
		this.imageFileRepository = imageFileRepository;
		this.userRepository = userRepository;
	}

	@Override
public ImageFile storeImageFile (MultipartFile file) {
	String fileName = StringUtils.cleanPath (file.getOriginalFilename ());
	try {
		if (fileName.contains ("..")) {
			throw new FileStorageException ("Sorry! Filename contains invalid path sequence " + fileName);
		}
		
		ImageFile imageFile = new ImageFile (fileName, file.getContentType (), file.getBytes ());
		
//		imageFile.setUser(userRepository.getOne (3L));
		
		return imageFileRepository.save (imageFile);
	} catch (IOException ex) {
		throw new FileStorageException ("Could not store file " + fileName + ". Please try again!", ex);
	}
}
@Override
public ImageFile storeImageFile (MultipartFile file, User user) {
	String fileName = StringUtils.cleanPath (file.getOriginalFilename ());
	try {
		if (fileName.contains ("..")) {
			throw new FileStorageException ("Sorry! Filename contains invalid path sequence " + fileName);
		}
		
		ImageFile imageFile = new ImageFile (fileName, file.getContentType (), file.getBytes ());
		
//		imageFile.setUser(user);
		
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
public ImageFile getProfilePicture (User user) {
	return null;
}
}
