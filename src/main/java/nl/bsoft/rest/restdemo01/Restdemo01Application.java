package nl.bsoft.rest.restdemo01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"nl.bsoft.rest.restdemo01.controller",
        "nl.bsoft.rest.restdemo01.domain",
        "nl.bsoft.rest.restdemo01.repository",
        "nl.bsoft.rest.restdemo01.service",
        "nl.bsoft.rest.restdemo01"})

public class Restdemo01Application {

    public static void main(String[] args) {
        SpringApplication.run(Restdemo01Application.class, args);
    }

}
