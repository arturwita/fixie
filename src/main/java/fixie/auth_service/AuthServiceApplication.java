package fixie.auth_service;

import java.util.Collections;

import fixie.common.GlobalConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(AuthServiceApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", GlobalConfig.AUTHORIZATION_SERVICE_PORT));
        app.run(args);
    }

}
