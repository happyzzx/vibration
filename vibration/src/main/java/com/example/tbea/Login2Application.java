package com.example.tbea;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;


//@EnableWebSecurity
//@EnableWebFluxSecurity
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })

public class Login2Application {

    public static void main(String[] args) {
        SpringApplication.run(Login2Application.class, args);
    }


}
