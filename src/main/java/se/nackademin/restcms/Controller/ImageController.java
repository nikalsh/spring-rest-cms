package se.nackademin.restcms.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.nackademin.restcms.CrudRepositories.ImageRepository;
import se.nackademin.restcms.ImageTest;

@RestController
public class ImageController {
    @Autowired
    ImageRepository imageRepository;

//    @PostMapping("/uploadFile")
//    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
//        String fileName = fileStorageService.storeFile(file);
//
//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/downloadFile/")
//                .path(fileName)
//                .toUriString();
//
//        return new UploadFileResponse(fileName, fileDownloadUri,
//                file.getContentType(), file.getSize());
//    }
@CrossOrigin(origins = "http://localhost:8081")
    @PostMapping("/uploadImage")
    public ResponseEntity<ImageTest> uploadImage() {
    ImageTest obj = new ImageTest();
    obj.setUrl("https://img.purch.com/w/660/aHR0cDovL3d3dy5saXZlc2NpZW5jZS5jb20vaW1hZ2VzL2kvMDAwLzEwNC84MTkvb3JpZ2luYWwvY3V0ZS1raXR0ZW4uanBn");
    return new ResponseEntity<>(obj,HttpStatus.OK);

    }

}
