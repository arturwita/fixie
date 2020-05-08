package fixie.activity_service;

import fixie.common.GlobalConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Collections;

@SpringBootApplication
@ComponentScan(basePackages = {"fixie.common", "fixie.activity_service"})
public class ActivityServiceApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ActivityServiceApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", GlobalConfig.ACTIVITY_SERVICE_PORT));
        app.run(args);
    }
}
