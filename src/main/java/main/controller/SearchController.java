package main.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Sadra
 * Created by Sadra on 1/18/16.
 */
@RestController
public class SearchController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}
