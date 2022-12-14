package com.cakemgr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableAuthorizationServer
@SpringBootApplication
@EnableSwagger2
public class CakeManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CakeManagerApplication.class, args);
    }
}
