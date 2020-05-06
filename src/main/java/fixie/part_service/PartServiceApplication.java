package fixie.part_service;

import fixie.common.GlobalConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Collections;

@SpringBootApplication
@ComponentScan(basePackages = {"fixie.common", "fixie.part_service"})
public class PartServiceApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(PartServiceApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", GlobalConfig.PART_SERVICE_PORT));
        app.run(args);
    }
}
