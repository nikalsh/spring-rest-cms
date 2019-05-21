package se.nackademin.restcms;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/")
    public String HelloWorld() {
        return "HELLO WORLD FROM SPRING BOOT";
    }

}
