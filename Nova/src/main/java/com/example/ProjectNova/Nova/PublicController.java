package com.example.ProjectNova.Nova;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
public class PublicController {

    @GetMapping(path="")
    public String welcome(){
        return ("<h1>Welcome</h1> <p>To project novus</p>");
    }
}
