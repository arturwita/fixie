package fixie.user_service;

import java.util.Collections;

import fixie.common.GlobalConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(UserServiceApplication.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", GlobalConfig.USER_SERVICE_PORT));
		app.run(args);
	}

}
