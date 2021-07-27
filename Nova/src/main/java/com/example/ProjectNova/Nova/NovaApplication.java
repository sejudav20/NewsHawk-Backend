package com.example.ProjectNova.Nova;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.example.ProjectNova.Nova"})
public class NovaApplication{

    public static void main(String[] args) {
        SpringApplication.run(NovaApplication.class, args);
        System.out.println("...Runnning");
    }

}
