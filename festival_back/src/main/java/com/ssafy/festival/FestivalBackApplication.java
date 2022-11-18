package com.ssafy.festival;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ssafy"})
@MapperScan(basePackages = { "com.ssafy.*.mapper" })
public class FestivalBackApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(FestivalBackApplication.class, args);
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				// TODO Auto-generated method stub
				registry.addMapping("/**").allowedOrigins("http://localhost:8080")
				.allowedMethods(
				    	HttpMethod.GET.name(),
				    	HttpMethod.HEAD.name(),
				    	HttpMethod.POST.name(),
				    	HttpMethod.PUT.name(),
				    	HttpMethod.DELETE.name());
			}
			
		};
	}

}

