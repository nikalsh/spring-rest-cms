package se.nackademin.restcms.Controller;



        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.core.io.ByteArrayResource;
        import org.springframework.core.io.Resource;
        import org.springframework.http.HttpHeaders;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.MediaType;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;
        import org.springframework.web.multipart.MultipartFile;
        import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
        import se.nackademin.restcms.Entities.ImageFile;
        import se.nackademin.restcms.CKResponse;
        import se.nackademin.restcms.Entities.Post;
        import se.nackademin.restcms.payload.UploadedImageResponse;
        import se.nackademin.restcms.service.ImageFileStorageServiceImpl;
        import se.nackademin.restcms.service.PostService;

@RestController
public class PostController {

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }


    @CrossOrigin(origins = "http://localhost:8081")
    @PostMapping("/uploadPost")
    public ResponseEntity<CKResponse> uploadImage(@RequestParam("file") String file) {
        System.out.println(file);
        Post post = postService.storePost (file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadPost/")
                .path(post.getId())
                .toUriString();
        return new ResponseEntity<>(new CKResponse(fileDownloadUri), HttpStatus.OK);

    }

    @GetMapping("/downloadPost/{postId}")
    public ResponseEntity<String> downloadFile(@PathVariable String postId) {
        // Load file from database
        Post post = postService.getPost(postId);

        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body( post.getPostData());
    }

}