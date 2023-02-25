package com.learn.security;

import com.learn.security.entity.User;
import com.learn.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringSecurityApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

	@Override
	public void run(String... args) {
		User user = User.builder()
				.username("dinesh")
				.password("dinesh")
				.email("dinesh@gmail.com")
				.role("ROLE_USER")
				.build();

		userService.save(user);

		user = User.builder()
				.username("rithwik")
				.password("rithwik")
				.email("rithwik@gmail.com")
				.role("ROLE_ADMIN")
				.build();
		userService.save(user);
	}
}
