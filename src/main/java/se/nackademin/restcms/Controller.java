package se.nackademin.restcms;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

//    @GetMapping("/")
//    public String HelloWorld() {
//        return "HELLO WORLD FROM SPRING BOOT";
//    }

    @GetMapping("/test")
    public String result() {
        return "endpoint exposed";
    }

    @GetMapping("/asd")
    public String res() {
        return "asd";
    }

    @RequestMapping("/requestTest")
    public String ress(){
        return "requestTest";
    }

}
