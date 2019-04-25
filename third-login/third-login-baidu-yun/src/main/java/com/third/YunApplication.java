package com.third;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Spring Boot 应用启动类
 */
@SpringBootApplication
public class YunApplication {
    public static void main(String[] args) {
        SpringApplication.run(YunApplication.class, args);
    }
    @Bean
    public RestTemplate restTemplate() {
    	return new RestTemplate();
    }
}