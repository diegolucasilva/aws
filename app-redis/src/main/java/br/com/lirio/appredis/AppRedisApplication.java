package br.com.lirio.appredis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@SpringBootApplication
public class AppRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppRedisApplication.class, args);
	}

	@Value("${spring.redis.host}")
	private String redishost;

	@GetMapping
	public String get() {
		return redishost;
	}
	

}
