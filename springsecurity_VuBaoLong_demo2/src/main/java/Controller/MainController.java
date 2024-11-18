package Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, everyone can access this!";
    }

    @GetMapping("/secure")
    public String secure() {
        return "Secure endpoint, login required!";
    }
}
