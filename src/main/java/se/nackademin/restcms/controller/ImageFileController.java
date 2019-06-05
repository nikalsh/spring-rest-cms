package se.nackademin.restcms.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import se.nackademin.restcms.entities.ImageFile;
import se.nackademin.restcms.service.UserService;
import se.nackademin.restcms.service.UserServiceImpl;
import se.nackademin.restcms.service.ImageFileStorageService;
import se.nackademin.restcms.service.ImageFileStorageServiceImpl;

@Slf4j
@RestController
@RequestMapping("/image")
public class ImageFileController {
    private final UserService blogAdminService;
    private final ImageFileStorageService imageFileStorageService;

    @Autowired
    public ImageFileController(final UserServiceImpl blogAdminService, final ImageFileStorageServiceImpl imageFileStorageService) {
        this.imageFileStorageService = imageFileStorageService;
        this.blogAdminService = blogAdminService;
    }

    @PostMapping("/uploadImage")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        ImageFile imageFile = imageFileStorageService.storeImageFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/image/downloadFile/")
                .path(imageFile.getId())
                .toUriString();
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(fileDownloadUri);

    }

    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
        ImageFile imageFile = imageFileStorageService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(imageFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + imageFile.getFileName() + "\"")
                .body(new ByteArrayResource(imageFile.getData()));
    }

}
